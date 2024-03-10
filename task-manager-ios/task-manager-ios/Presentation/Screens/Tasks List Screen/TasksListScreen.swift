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
        Group {
            if viewModel.state.isLoading {
                ProgressView()
                
            } else if viewModel.state.tasks.isEmpty  {
                Text("No tasks yet")
                
            } else {
                List(viewModel.state.tasks) { task in
                    TaskItem(task: task)
                        .contentShape(Rectangle())
                        .onTapGesture {
                            viewModel.state.activeSheet = .constant(.modify(task))
                        }
                }
                .refreshable {
                    viewModel.getTasks(isFromSwipeRefresh: true)
                }
            }
        }
        .navigationTitle("Tasks List")
        .toolbar {
            Button(
                action: {
                    viewModel.state.activeSheet = .constant(.add)
                },
                label: {
                    Image(systemName: "plus")
                }
            )
        }
    }
}

#Preview {
    NavigationStack {
        TasksListScreen()
            .environmentObject(TasksListViewModel.sample)
    }
}
