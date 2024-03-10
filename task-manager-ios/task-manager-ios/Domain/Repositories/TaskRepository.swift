//
//  TaskRepository.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

protocol TaskRepository {
    func getTasks() -> Result<[Task], Error>
    func addTask(_ task: Task) -> Result<Void, Error>
    func editTask(_ task: Task) -> Result<Void, Error>
}
