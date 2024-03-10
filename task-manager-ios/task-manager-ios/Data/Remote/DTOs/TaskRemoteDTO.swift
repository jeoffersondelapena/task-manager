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
    
    static func toDTO(_ task: Task) -> TaskRemoteDTO {
        TaskRemoteDTO(
            id: task.id,
            title: task.title,
            description: task.description,
            deadline: task.deadline,
            isCompleted: task.isCompleted
        )
    }
    
    static func toDTO(_ tasks: [Task]) -> [TaskRemoteDTO] {
        tasks.map { task in
            toDTO(task)
        }
    }
}

extension Array<TaskRemoteDTO> {
    func toDomain() -> [Task] {
        map { taskRemoteDTO in
            taskRemoteDTO.toDomain()
        }
    }
}
