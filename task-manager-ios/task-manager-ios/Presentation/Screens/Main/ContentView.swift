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
    
    private var isActiveAlertAnError: Bool {
        if case .error = viewModel.state.activeAlert.wrappedValue {
            return true
        } else {
            return false
        }
    }
    
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
        .sheet(
            isPresented: .constant(viewModel.state.activeSheet.wrappedValue != nil),
            onDismiss: {
                viewModel.state.activeSheet = .constant(nil)
            },
            content: {
                AddEditTaskSheet(type: viewModel.state.activeSheet.wrappedValue ?? .add)
                    .presentationDetents([.medium])
                    .environmentObject(viewModel)
            }
        )
        .alert(isPresented: .constant(isActiveAlertAnError)) {
            guard case .error(let error) = viewModel.state.activeAlert.wrappedValue else {
                return ErrorAlert.body {
                    viewModel.state.activeAlert = .constant(nil)
                }
            }
            return ErrorAlert.body(error: error) {
                viewModel.state.activeAlert = .constant(nil)
            }
        }
    }
}

#Preview {
    ContentView()
}
