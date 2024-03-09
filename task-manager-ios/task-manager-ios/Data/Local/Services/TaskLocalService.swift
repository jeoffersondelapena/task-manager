//
//  TaskLocalService.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation
import RealmSwift

class TaskLocalService {
    func getTasks() -> Result<[TaskLocalDTO], Error> {
        do {
            // TODO(jeofferson): Delete this
            print("Realm is located at:", try Realm().configuration.fileURL!)
            
            return .success(Array((try Realm()).objects(TaskLocalDTO.self)))
        } catch let error as NSError {
            return .failure(error)
        }
    }
    
    func setTasks(taskLocalDTOs: [TaskLocalDTO]) -> Result<Void, Error> {
        do {
            let realm = try Realm()
            try realm.write {
                realm.add(taskLocalDTOs)
            }
            return .success(())
        } catch let error as NSError {
            return .failure(error)
        }
    }
}
