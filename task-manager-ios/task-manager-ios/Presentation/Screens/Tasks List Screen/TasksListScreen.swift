//
//  TasksListScreen.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct TasksListScreen: View {
    @StateObject private var viewModel = TasksListViewModel(
        repository: TaskRepositoryImpl(
            remoteService: TaskRemoteService(),
            localService: TaskLocalService()
        )
    )
    
    var body: some View {
        List(viewModel.state.tasks) { task in
            TaskItem(task: task)
        }
        .navigationTitle("Tasks List Screen")
        .onAppear {
            viewModel.getTasks()
        }
    }
}

#Preview {
    TasksListScreen()
}
