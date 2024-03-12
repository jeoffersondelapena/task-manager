//
//  ErrorAlert.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/11/24.
//

import SwiftUI

struct ErrorAlert {
    static func body(error: TaskManagerError? = nil, action: @escaping () -> Void) -> Alert {
        Alert(
            title: Text(error?.description ?? "An error has occured"),
            dismissButton: .default(Text("OK"), action: action)
        )
    }
}
