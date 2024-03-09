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
}

extension Array<TaskLocalDTO> {
    func toDomain() -> [Task] {
        map { taskLocalDTO in
            taskLocalDTO.toDomain()
        }
    }
}
