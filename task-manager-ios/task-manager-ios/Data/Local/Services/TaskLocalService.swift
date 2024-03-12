//
//  TaskLocalService.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation
import RealmSwift

class TaskLocalService: BaseLocalService {
    func getTasks() -> Result<[TaskLocalDTO], TaskManagerError> {
        handleErrors {
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
    
    func addTask(_ taskLocalDTO: TaskLocalDTO) -> Result<Void, TaskManagerError> {
        handleErrors {
            let realm = try Realm()
            
            try realm.write {
                realm.add(taskLocalDTO)
            }
            
            return .success(())
        }
    }
    
    func addTask(_ taskLocalDTOs: [TaskLocalDTO]) -> Result<Void, TaskManagerError> {
        var error: TaskManagerError?
        
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
    
    func editTask(_ taskLocalDTO: TaskLocalDTO) -> Result<Void, TaskManagerError> {
        handleErrors {
            let realm = try Realm()
            
            let taskLocalDTOToEdit = realm.object(ofType: TaskLocalDTO.self, forPrimaryKey: taskLocalDTO._id)
            
            guard let taskLocalDTOToEdit = taskLocalDTOToEdit else {
                return .failure(.taskNotFound)
            }
            
            try realm.write {
                taskLocalDTOToEdit.title = taskLocalDTO.title
                taskLocalDTOToEdit.desc = taskLocalDTO.desc
                taskLocalDTOToEdit.deadline = taskLocalDTO.deadline
                taskLocalDTOToEdit.isCompleted = taskLocalDTO.isCompleted
            }
            
            return .success(())
        }
    }
    
    func toggleTaskCompletion(_ taskLocalDTO: TaskLocalDTO) -> Result<Void, TaskManagerError> {
        handleErrors {
            let realm = try Realm()
            
            let taskLocalDTOToToggleCompletion = realm.object(ofType: TaskLocalDTO.self, forPrimaryKey: taskLocalDTO._id)
            
            guard let taskLocalDTOToToggleCompletion = taskLocalDTOToToggleCompletion else {
                return .failure(.taskNotFound)
            }
            
            try realm.write {
                taskLocalDTOToToggleCompletion.isCompleted = !taskLocalDTO.isCompleted
            }
            
            return .success(())
        }
    }
    
    func deleteTask(_ taskLocalDTO: TaskLocalDTO) -> Result<Void, TaskManagerError> {
        handleErrors {
            let realm = try Realm()
            
            let taskLocalDTOToDelete = realm.object(ofType: TaskLocalDTO.self, forPrimaryKey: taskLocalDTO._id)
            
            guard let taskLocalDTOToDelete = taskLocalDTOToDelete else {
                return .failure(.taskNotFound)
            }
            
            try realm.write {
                realm.delete(taskLocalDTOToDelete)
            }
            
            return .success(())
        }
    }
}
