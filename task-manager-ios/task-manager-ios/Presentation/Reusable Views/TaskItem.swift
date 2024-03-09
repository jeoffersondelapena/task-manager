//
//  TaskItem.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct TaskItem: View {
    @State var task: Task
    
    var body: some View {
        return HStack(spacing: 4) {
            VStack(alignment: .leading, spacing: 4) {
                Text(task.title)
                    .font(.headline)
                    .strikethrough(task.isCompleted)
                
                if let description = task.description {
                    Text(description)
                        .lineLimit(3)
                        .font(.caption)
                        .strikethrough(task.isCompleted)
                }
                
                if let deadline = task.deadline {
                    Text("Deadline: \(DateTimeManager.dateToMmmmDdYyyy(deadline))")
                        .font(.subheadline)
                        .strikethrough(task.isCompleted)
                }
            }
            
            Spacer()
            
            CheckboxView(isChecked: $task.isCompleted)
        }
    }
}

#Preview {
    TaskItem(task: Task.sample)
}
