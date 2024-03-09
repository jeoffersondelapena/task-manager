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
    let isCompleted: Bool
}
