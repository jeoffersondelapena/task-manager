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
            let tasks = (try Realm()).objects(TaskLocalDTO.self).sorted(by: \.deadline)
            
            let tasksWithDeadline = tasks.where { task in
                task.deadline != nil
            }
            let tasksWithoutDeadline = tasks.where { task in
                task.deadline == nil
            }
            
            
            return .success(Array(tasksWithDeadline) + Array(tasksWithoutDeadline))
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
    
    func setTasks(_ taskLocalDTOs: [TaskLocalDTO]) -> Result<Void, Error> {
        serviceCall {
            let realm = try Realm()
            
            try realm.write {
                realm.add(taskLocalDTOs)
            }
            
            return .success(())
        }
    }
}
