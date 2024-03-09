//
//  TaskRemoteDTO.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

class TaskRemoteDTO {
    let id: String?
    let title: String
    let description: String?
    let deadline: Date?
    let isCompleted: Bool
    
    init(
        id: String?,
        title: String,
        description: String?,
        deadline: Date?,
        isCompleted: Bool
    ) {
        self.id = id
        self.title = title
        self.description = description
        self.deadline = deadline
        self.isCompleted = isCompleted
    }
    
    func toDomain() -> Task {
        Task(
            id: id,
            title: title,
            description: description,
            deadline: deadline,
            isCompleted: isCompleted
        )
    }
}

extension Array<TaskRemoteDTO> {
    func toDomain() -> [Task] {
        map { taskRemoteDTO in
            taskRemoteDTO.toDomain()
        }
    }
}
