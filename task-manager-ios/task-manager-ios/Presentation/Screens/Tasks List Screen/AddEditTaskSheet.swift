//
//  AddEditTaskSheet.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct AddEditTaskSheet: View {
    @Environment(\.dismiss) var dismiss
    
    @State private var title = ""
    
    @State private var addDeadline = false
    @State private var deadline = Date()
    
    @State private var description = ""
    
    let type: TasksListState.SheetType
    
    private var isModify: Bool {
        if case .modify = type {
            return true
        } else {
            return false
        }
    }
    
    @State private var isEditing = false
    
    private var isDisabledFields: Bool {
        isModify && !isEditing
    }
    
    private var fieldsOpacity: Double {
        isDisabledFields ? 0.3 : 1
    }
    
    private var isDisabledDatePicker: Bool {
        (isModify && !isEditing) || !addDeadline
    }
    
    private var datePickerOpacity: Double {
        isDisabledDatePicker ? 0.3 : 1
    }
    
    var body: some View {
        VStack(spacing: 8) {
            TextField("Task title", text: $title)
                .textFieldStyle(.roundedBorder)
                .opacity(fieldsOpacity)
                .disabled(isDisabledFields)
            
            Toggle("Add deadline", isOn: $addDeadline)
                .opacity(fieldsOpacity)
                .disabled(isDisabledFields)
            
            DatePicker(
                "Deadline",
                selection: $deadline,
                in: Date.now...,
                displayedComponents: .date
            )
            .opacity(datePickerOpacity)
            .disabled(isDisabledDatePicker)
            
            TextField("Task description", text: $description, axis: .vertical)
                .textFieldStyle(.roundedBorder)
                .lineLimit(5...10)
                .opacity(fieldsOpacity)
                .disabled(isDisabledFields)
            
            Spacer()
            
            switch type {
            case .add:
                Button("Save") {
                    
                }
                .frame(maxWidth: .infinity)
                
            case .modify:
                if !isEditing {
                    Button("Complete task") {
                        
                    }
                    .frame(maxWidth: .infinity)
                }
                
                HStack(spacing: 8) {
                    if !isEditing {
                        Button("Edit") {
                            isEditing = true
                        }
                        .frame(maxWidth: .infinity)
                        
                    } else {
                        Button("Save") {
                            
                        }
                        .frame(maxWidth: .infinity)
                    }
                    
                    Button("Delete") {
                        
                    }
                    .frame(maxWidth: .infinity)
                    .foregroundColor(.red)
                }
            }
            
            
        }
        .padding()
        .onAppear {
            guard case let .modify(task) = type else {
                return
            }
            title = task.title
            deadline = task.deadline ?? Date()
            description = task.description ?? ""
        }
    }
}

#Preview {
    AddEditTaskSheet(type: .modify(Task.sample))
}
