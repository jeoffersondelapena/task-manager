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
            state.error = error
        }
        
        if !isFromSwipeRefresh {
            state.isLoading = false
        }
    }
}
