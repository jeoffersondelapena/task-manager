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
        Group {
            if viewModel.state.isLoading {
                ProgressView()
                
            } else if viewModel.state.tasks.isEmpty  {
                Text("No completed tasks yet")
                
            } else {
                List(viewModel.state.completedTasks) { task in
                    TaskItem(task: task, allowStrikethrough: false)
                        .contentShape(Rectangle())
                        .onTapGesture {
                            viewModel.state.activeSheet = .constant(.modify(task))
                        }
                }
                .listStyle(.plain)
                .refreshable {
                    viewModel.getTasks(isFromSwipeRefresh: true)
                }
            }
        }
        .navigationTitle("Completed Tasks List")
        .navigationBarTitleDisplayMode(.inline)
    }
}

#Preview {
    NavigationStack {
        CompletedTasksListScreen()
            .environmentObject(TasksListViewModel.sample)
    }
}
