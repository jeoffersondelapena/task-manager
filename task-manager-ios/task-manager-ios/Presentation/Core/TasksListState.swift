//
//  TasksListState.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct TasksListState {
    enum SheetType {
        case add
        case modify(Task)
    }
    
    enum AlertType {
        case deleteConfirmation(Task)
        case error(TaskManagerError)
    }
    
    var tasks: [Task] = []
    var completedTasks: [Task] {
        tasks.filter { task in
            task.isCompleted
        }
    }
    
    var isLoading = false
    
    var activeSheet: Binding<SheetType?> = .constant(nil)
    var activeAlert: Binding<AlertType?> = .constant(nil)
}
