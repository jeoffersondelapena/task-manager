//
//  TaskRemoteService.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

class TaskRemoteService {
    func getTasks() -> Result<[TaskRemoteDTO], TaskManagerError> {
        // let taskRemoteDTOs = Network call here...
        let taskRemoteDTOs = [
            TaskRemoteDTO(
                id: "abc",
                title: "Dummy title 1",
                description: "Dummy description",
                deadline: Date(),
                isCompleted: true
            ),
            TaskRemoteDTO(
                id: "def",
                title: "Dummy title 2",
                description: nil,
                deadline: nil,
                isCompleted: false
            ),
        ]
        return .success(taskRemoteDTOs)
    }
}
