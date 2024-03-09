//
//  TaskItem.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct TaskItem: View {
    @State var task: Task
    let allowStrikethrough: Bool
    
    init(task: Task, allowStrikethrough: Bool = true) {
        self.task = task
        self.allowStrikethrough = allowStrikethrough
    }
    
    private var showStrikethrough: Bool {
        allowStrikethrough && task.isCompleted
    }
    
    var body: some View {
        return HStack(spacing: 4) {
            VStack(alignment: .leading, spacing: 4) {
                Text(task.title)
                    .font(.headline)
                    .strikethrough(showStrikethrough)
                
                if let description = task.description {
                    Text(description)
                        .lineLimit(3)
                        .font(.caption)
                        .strikethrough(showStrikethrough)
                }
                
                if let deadline = task.deadline {
                    Text("Deadline: \(DateTimeManager.dateToMmmmDdYyyy(deadline))")
                        .font(.subheadline)
                        .strikethrough(showStrikethrough)
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