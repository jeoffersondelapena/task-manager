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
        .sheet(
            isPresented: .constant(viewModel.state.activeSheet.wrappedValue != nil),
            onDismiss: {
                viewModel.state.activeSheet = .constant(nil)
            },
            content: {
                AddEditTaskSheet(type: viewModel.state.activeSheet.wrappedValue ?? .add)
                    .presentationDetents([.medium])
            }
        )
    }
}

#Preview {
    NavigationStack {
        CompletedTasksListScreen()
            .environmentObject(
                TasksListViewModel(
                    repository: TaskRepositoryImpl(
                        remoteService: TaskRemoteService(),
                        localService: TaskLocalService()
                    )
                )
            )
    }
}
