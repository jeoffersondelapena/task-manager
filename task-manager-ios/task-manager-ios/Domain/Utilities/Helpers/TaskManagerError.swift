//
//  TaskManagerError.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/10/24.
//

import Foundation

enum TaskManagerError: Error {
    case generic(String)
    case taskNotFound
}

extension TaskManagerError {
    var description: String {
        switch self {
        case .taskNotFound:
            return "Task not found"
        case .generic(let description):
            return description
        }
    }
}
