//
//  TaskLocalService.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation
import RealmSwift

class TaskLocalService: BaseService {
    func getTasks() -> Result<[TaskLocalDTO], Error> {
        serviceCall {
            let taskLocalDTOs = Array((try Realm()).objects(TaskLocalDTO.self).sorted(by: \.deadline))
            
            let taskLocalDTOsWithDeadline = taskLocalDTOs.filter { taskLocalDTO in
                taskLocalDTO.deadline != nil
            }
            let taskLocalDTOsWithoutDeadline = taskLocalDTOs.filter { taskLocalDTO in
                taskLocalDTO.deadline == nil
            }
            
            return .success(taskLocalDTOsWithDeadline + taskLocalDTOsWithoutDeadline)
        }
    }
    
    func addTask(_ taskLocalDTO: TaskLocalDTO) -> Result<Void, Error> {
        serviceCall {
            let realm = try Realm()
            
            try realm.write {
                realm.add(taskLocalDTO)
            }
            
            return .success(())
        }
    }
    
    func addTask(_ taskLocalDTOs: [TaskLocalDTO]) -> Result<Void, Error> {
        var error: Error?
        
        taskLocalDTOs.forEach { taskLocalDTO in
            if case .failure(let e) = addTask(taskLocalDTO) {
                error = e
            }
        }
        
        if let error = error {
            return .failure(error)
        }
        
        return .success(())
    }
}
