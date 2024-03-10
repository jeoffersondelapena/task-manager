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
            id: _id.stringValue,
            title: title,
            description: desc,
            deadline: deadline,
            isCompleted: isCompleted
        )
    }
    
    static func toData(_ task: Task) -> TaskLocalDTO {
        let taskLocalDTO = TaskLocalDTO()
        
        if let taskID = task.id, let id = try? ObjectId(string: taskID) {
            taskLocalDTO._id = id
        }
        taskLocalDTO.title = task.title
        taskLocalDTO.desc = task.description
        taskLocalDTO.deadline = task.deadline
        taskLocalDTO.isCompleted = task.isCompleted
        
        return taskLocalDTO
    }
    
    static func toData(_ tasks: [Task]) -> [TaskLocalDTO] {
        tasks.map { task in
            toData(task)
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
