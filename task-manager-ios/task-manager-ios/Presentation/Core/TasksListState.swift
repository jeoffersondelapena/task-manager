//
//  TasksListState.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct TasksListState {
    var tasks: [Task] = []
    var completedTasks: [Task] {
        tasks.filter { task in
            task.isCompleted
        }
    }
    
    var isLoading = false
    
    var error: Binding<Error?> = .constant(nil)
}
