//
//  ContentView.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        TabView {
            NavigationStack {
                TasksListScreen()
            }
            .tabItem {
                Label(
                    "Tasks",
                    systemImage: "checklist"
                )
            }
            
            NavigationStack {
                CompletedTasksListScreen()
            }
            .tabItem {
                Label(
                    "Completed Tasks",
                    systemImage: "checklist.checked"
                )
            }
        }
    }
}

#Preview {
    ContentView()
}
