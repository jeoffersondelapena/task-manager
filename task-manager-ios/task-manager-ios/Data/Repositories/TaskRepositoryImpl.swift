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
        // In a repository pattern, this is where you’ll add the condition
        // to decide whether to fetch the data from a remote source or from a local source (Realm)

        // That condition is most likely if the user is connected to the internet or not

        // In our case, since fetching from network and syncing with cache is not included in the requirements,
        // I’ll just set it to always fetch the data from the local source (Realm)
        if (true) {
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
        var taskLocalDTOs: [TaskLocalDTO] = []
        
        taskRemoteDTOs.forEach { taskRemoteDTO in
            let taskLocalDTO = TaskLocalDTO()
            
            taskLocalDTO.title = taskRemoteDTO.title
            taskLocalDTO.desc = taskRemoteDTO.description
            taskLocalDTO.deadline = taskRemoteDTO.deadline
            taskLocalDTO.isCompleted = taskRemoteDTO.isCompleted
            
            taskLocalDTOs.append(taskLocalDTO)
        }
        
        _ = localService.setTasks(taskLocalDTOs: taskLocalDTOs)
    }
}
