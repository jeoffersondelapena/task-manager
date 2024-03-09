//
//  TasksListState.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

struct TasksListState {
    var tasks: [Task] = []
    var isLoading = false
    var error: Error?
}
