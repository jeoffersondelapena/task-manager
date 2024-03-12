//
//  TaskRemoteService.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

class TaskRemoteService {
    func getTasks() -> Result<[TaskRemoteDTO], TaskManagerError> {
        // Network logic here...
        return .success([])
    }
    
    func addTask(_ taskRemoteDTO: TaskRemoteDTO) -> Result<Void, TaskManagerError> {
        // Network logic here...
        return .success(())
    }
    
    func editTask(_ taskRemoteDTO: TaskRemoteDTO) -> Result<Void, TaskManagerError> {
        // Network logic here...
        return .success(())
    }
    
    func toggleTaskCompletion(_ taskRemoteDTO: TaskRemoteDTO) -> Result<Void, TaskManagerError> {
        // Network logic here...
        return .success(())
    }
    
    func deleteTask(_ taskRemoteDTO: TaskRemoteDTO) -> Result<Void, TaskManagerError> {
        // Network logic here...
        return .success(())
    }
}
