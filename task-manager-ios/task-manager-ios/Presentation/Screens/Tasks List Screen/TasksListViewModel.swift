//
//  TasksListViewModel.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

class TasksListViewModel: ObservableObject {
    @Published var tasksListState = TasksListState()
    
    private let repository: TaskRepository
    
    init(repository: TaskRepository) {
        self.repository = repository
    }
    
    func getTasks() {
        tasksListState.isLoading = true
        
        switch repository.getTasks() {
        case .success(let tasks):
            tasksListState.tasks = tasks
        case .failure(let error):
            tasksListState.error = error
        }
        
        tasksListState.isLoading = false
    }
}
