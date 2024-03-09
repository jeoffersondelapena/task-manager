//
//  TasksListScreen.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct TasksListScreen: View {
    @EnvironmentObject private var viewModel: TasksListViewModel
    
    var body: some View {
        List(viewModel.state.tasks) { task in
            TaskItem(task: task)
        }
        .navigationTitle("Tasks List")
    }
}

#Preview {
    TasksListScreen()
}
