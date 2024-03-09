//
//  Task.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

struct Task: Identifiable {
    let id: String?
    let title: String
    let description: String?
    let deadline: Date?
    var isCompleted: Bool
    
    static let sample = Task(
        id: "abc",
        title: "Dummy title",
        description: "Dummy description",
        deadline: Date(),
        isCompleted: true
    )
}
