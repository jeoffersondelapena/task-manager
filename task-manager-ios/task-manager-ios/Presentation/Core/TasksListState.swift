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
    
    var tasks: [Task] = []
    var completedTasks: [Task] {
        tasks.filter { task in
            task.isCompleted
        }
    }
    
    var isLoading = false
    
    var activeSheet: Binding<SheetType?> = .constant(nil)
    
    var error: Binding<Error?> = .constant(nil)
}
