//
//  ContentView.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct ContentView: View {
    @StateObject private var viewModel = TasksListViewModel(
        repository: TaskRepositoryImpl(
            remoteService: TaskRemoteService(),
            localService: TaskLocalService()
        )
    )
    
    var body: some View {
        TabView {
            NavigationStack {
                TasksListScreen()
                    .environmentObject(viewModel)
            }
            .tabItem {
                Label(
                    "Tasks",
                    systemImage: "checklist"
                )
            }
            
            NavigationStack {
                CompletedTasksListScreen()
                    .environmentObject(viewModel)
            }
            .tabItem {
                Label(
                    "Completed Tasks",
                    systemImage: "checklist.checked"
                )
            }
        }
        .onAppear {
            viewModel.getTasks()
        }
        .alert(
            viewModel.state.error.wrappedValue?.localizedDescription ?? "An error has occurred.",
            isPresented: .constant(viewModel.state.error.wrappedValue != nil)
        ) {
            Button("OK") {
                viewModel.state.error = .constant(nil)
            }
        }
    }
}

#Preview {
    ContentView()
}
