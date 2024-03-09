//
//  TaskRemoteService.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

class TaskRemoteService {
    func getTasks() -> Result<[TaskRemoteDTO], Error> {
        let taskRemoteDTOs = /* someNetworkCall() */ [
            TaskRemoteDTO(
                id: "abc",
                title: "Dummy Title 1",
                description: "Dummy Description",
                deadline: Date(),
                isCompleted: true
            ),
            TaskRemoteDTO(
                id: "def",
                title: "Dummy Title 2",
                description: nil,
                deadline: nil,
                isCompleted: false
            ),
        ]
        return .success(taskRemoteDTOs)
    }
}
