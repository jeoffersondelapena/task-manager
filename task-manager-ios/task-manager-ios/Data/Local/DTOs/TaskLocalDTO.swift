//
//  TaskLocalDTO.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation
import RealmSwift

class TaskLocalDTO: Object {
    @Persisted(primaryKey: true) var _id: ObjectId
    @Persisted var title: String
    @Persisted var desc: String?
    @Persisted var deadline: Date?
    @Persisted var isCompleted: Bool
    
    func toDomain() -> Task {
        Task(
            id:_id.stringValue,
            title: title,
            description: desc,
            deadline: deadline,
            isCompleted: isCompleted
        )
    }
    
    static func toDTO(_ task: Task) -> TaskLocalDTO {
        let taskLocalDTO = TaskLocalDTO()
        
        taskLocalDTO.title = task.title
        taskLocalDTO.desc = task.description
        taskLocalDTO.deadline = task.deadline
        taskLocalDTO.isCompleted = task.isCompleted
        
        return taskLocalDTO
    }
    
    static func toDTO(_ tasks: [Task]) -> [TaskLocalDTO] {
        tasks.map { task in
            toDTO(task)
        }
    }
}

extension Array<TaskLocalDTO> {
    func toDomain() -> [Task] {
        map { taskLocalDTO in
            taskLocalDTO.toDomain()
        }
    }
}
