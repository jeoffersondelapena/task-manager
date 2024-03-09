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
    
    func getTasks() -> Result<[Task], Error> {
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
    
    private func cacheTasks(_ taskRemoteDTOs: [TaskRemoteDTO]) {
        _ = localService.setTasks(
            taskRemoteDTOs.map { taskRemoteDTO in
                let taskLocalDTO = TaskLocalDTO()
                
                taskLocalDTO.title = taskRemoteDTO.title
                taskLocalDTO.desc = taskRemoteDTO.description
                taskLocalDTO.deadline = taskRemoteDTO.deadline
                taskLocalDTO.isCompleted = taskRemoteDTO.isCompleted
                
                return taskLocalDTO
            }
        )
    }
}
