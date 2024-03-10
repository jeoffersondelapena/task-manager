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
    
    func addTask(_ task: Task) -> Result<Void, Error> {
        if /* !hasInternetConnection */ true {
            return localService.addTask(TaskLocalDTO.toDTO(task))
        } else {
            // Network logic here...
        }
    }
    
    private func cacheTasks(_ taskRemoteDTOs: [TaskRemoteDTO]) {
        _ = localService.addTask(
            TaskLocalDTO.toDTO(taskRemoteDTOs.toDomain())
        )
    }
}
