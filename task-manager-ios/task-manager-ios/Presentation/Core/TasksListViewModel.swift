//
//  TasksListViewModel.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

class TasksListViewModel: ObservableObject {
    @Published var state = TasksListState()
    
    private let repository: TaskRepository
    
    init(repository: TaskRepository) {
        self.repository = repository
    }
    
    func getTasks(isFromSwipeRefresh: Bool = false) {
        if !isFromSwipeRefresh {
            state.isLoading = true
        }
        
        switch repository.getTasks() {
        case .success(let tasks):
            state.tasks = tasks
        case .failure(let error):
            state.error = .constant(error)
        }
        
        if !isFromSwipeRefresh {
            state.isLoading = false
        }
    }
    
    func addTask(_ task: Task) {
        state.isLoading = true
        
        switch repository.addTask(task) {
        case .success:
            state.activeSheet = .constant(nil)
            getTasks()
        case .failure(let error):
            state.error = .constant(error)
        }
        
        state.isLoading = false
    }
    
    static let sample = TasksListViewModel(
        repository: TaskRepositoryImpl(
            remoteService: TaskRemoteService(),
            localService: TaskLocalService()
        )
    )
}
