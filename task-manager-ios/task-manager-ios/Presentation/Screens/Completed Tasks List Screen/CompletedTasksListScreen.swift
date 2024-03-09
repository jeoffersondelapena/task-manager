//
//  CompletedTasksListScreen.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct CompletedTasksListScreen: View {
    @EnvironmentObject private var viewModel: TasksListViewModel
    
    var body: some View {
        List(viewModel.state.completedTasks) { task in
            TaskItem(task: task, allowStrikethrough: false)
        }
        .listStyle(.plain)
        .navigationTitle("Completed Tasks List")
        .navigationBarTitleDisplayMode(.inline)
    }
}

#Preview {
    CompletedTasksListScreen()
}
