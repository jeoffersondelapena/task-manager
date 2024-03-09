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
            .success(Array((try Realm()).objects(TaskLocalDTO.self)))
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
