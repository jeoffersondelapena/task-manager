//
//  AddEditTaskSheet.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct AddEditTaskSheet: View {
    @Environment(\.dismiss) var dismiss
    
    @EnvironmentObject private var viewModel: TasksListViewModel
    
    @State private var title = ""
    @State private var titleErrorMessage = ""
    
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
            if viewModel.state.isLoading {
                ProgressView()
            }
            
            VStack(alignment: .leading, spacing: 0) {
                TextField("Task title", text: $title)
                    .textFieldStyle(.roundedBorder)
                    .opacity(fieldsOpacity)
                    .disabled(isDisabledFields)
                
                if titleErrorMessage.isNotBlank() {
                    Text(titleErrorMessage)
                        .font(.caption)
                        .foregroundColor(Color(UIColor.systemRed))
                        .onChange(of: title) {
                            titleErrorMessage = ""
                        }
                }
            }
            
            Toggle("Add deadline", isOn: $addDeadline)
                .opacity(fieldsOpacity)
                .disabled(isDisabledFields)
            
            DatePicker(
                "Deadline",
                selection: $deadline,
                displayedComponents: .date
            )
            .opacity(datePickerOpacity)
            .disabled(isDisabledDatePicker)
            
            TextField("Task description", text: $description, axis: .vertical)
                .lineLimit(5...10)
                .textFieldStyle(.roundedBorder)
                .opacity(fieldsOpacity)
                .disabled(isDisabledFields)
            
            Spacer()
            
            switch type {
            case .add:
                Button("Save") {
                    guard let task = validateTask() else {
                        return
                    }
                    viewModel.addTask(task)
                }
                .frame(maxWidth: .infinity)
                
            case .modify(let task):
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
                            guard let task = validateTask() else {
                                return
                            }
                            viewModel.editTask(task)
                        }
                        .frame(maxWidth: .infinity)
                    }
                    
                    Button("Delete") {
                        viewModel.deleteTask(task)
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
            addDeadline = task.deadline != nil
            deadline = task.deadline ?? Date()
            description = task.description ?? ""
        }
    }
    
    private func validateTask() -> Task? {
        guard title.isNotBlank() else {
            titleErrorMessage = "Title should not be blank"
            return nil
        }
        switch type {
        case .add:
            return Task(
                id: nil,
                title: title,
                description: description.isBlank() ? nil : description,
                deadline: !addDeadline ? nil : deadline,
                isCompleted: false
            )
        case .modify(let task):
            return Task(
                id: task.id,
                title: title,
                description: description.isBlank() ? nil : description,
                deadline: !addDeadline ? nil : deadline,
                isCompleted: task.isCompleted
            )
        }
    }
}

#Preview {
    AddEditTaskSheet(type: .modify(Task.sample))
        .environmentObject(TasksListViewModel.sample)
}
