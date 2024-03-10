//
//  TaskRepository.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

protocol TaskRepository {
    func getTasks() -> Result<[Task], TaskManagerError>
    func addTask(_ task: Task) -> Result<Void, TaskManagerError>
    func editTask(_ task: Task) -> Result<Void, TaskManagerError>
}
