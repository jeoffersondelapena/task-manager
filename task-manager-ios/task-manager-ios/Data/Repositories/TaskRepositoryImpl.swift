//
//  TaskRepositoryImpl.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation
import RealmSwift

class TaskRepositoryImpl: TaskRepository {
    private let remoteService: TaskRemoteService
    private let localService: TaskLocalService
    
    init(remoteService: TaskRemoteService, localService: TaskLocalService) {
        self.remoteService = remoteService
        self.localService = localService
    }
    
    func getTasks() -> Result<[Task], TaskManagerError> {
        if /* !hasInternetConnection */ true {
            switch localService.getTasks() {
            case .success(let taskLocalDTOs):
                return .success(taskLocalDTOs.toDomain())
            case .failure(let error):
                return .failure(error)
            }
        } else {
            switch remoteService.getTasks() {
            case .success(let taskRemoteDTOs):
                cacheTasks(taskRemoteDTOs)
                return .success(taskRemoteDTOs.toDomain())
            case .failure(let error):
                return .failure(error)
            }
        }
    }
    
    func addTask(_ task: Task) -> Result<Void, TaskManagerError> {
        if /* !hasInternetConnection */ true {
            return localService.addTask(TaskLocalDTO.toData(task))
        } else {
            // Network logic here...
        }
    }
    
    func editTask(_ task: Task) -> Result<Void, TaskManagerError> {
        if /* !hasInternetConnection */ true {
            return localService.editTask(TaskLocalDTO.toData(task))
        } else {
            // Network logic here...
        }
    }
    
    func toggleTaskCompletion(_ task: Task) -> Result<Void, TaskManagerError> {
        if /* !hasInternetConnection */ true {
            return localService.toggleTaskCompletion(TaskLocalDTO.toData(task))
        } else {
            // Network logic here...
        }
    }
    
    func deleteTask(_ task: Task) -> Result<Void, TaskManagerError> {
        if /* !hasInternetConnection */ true {
            return localService.deleteTask(TaskLocalDTO.toData(task))
        } else {
            // Network logic here...
        }
    }
    
    private func cacheTasks(_ taskRemoteDTOs: [TaskRemoteDTO]) {
        _ = localService.addTask(
            TaskLocalDTO.toData(taskRemoteDTOs.toDomain())
        )
    }
}
