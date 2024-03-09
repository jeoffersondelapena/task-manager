//
//  TaskRemoteService.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

class TaskRemoteService {
    func getTasks() -> Result<[TaskRemoteDTO], Error> {
        // This is where you'll add the logic for fetching items from a remote source
        
        // Since fetching from network is not included in the requirements,
        // I'll just set this to always return dummy data
        .success([
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
        ])
    }
}
