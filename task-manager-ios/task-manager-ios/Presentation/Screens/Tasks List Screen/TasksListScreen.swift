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
        VStack {
            Text("Tasks List Screen")
            List(viewModel.state.tasks) { task in
                Text(task.title)
            }
        }
        .onAppear {
            viewModel.getTasks()
        }
    }
}

#Preview {
    TasksListScreen()
}
