//
//  CheckboxView.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct CheckboxView: View {
    let isChecked: Bool
    let onTap: () -> Void

    var body: some View {
        Image(systemName: isChecked ? "checkmark.square.fill" : "square")
            .foregroundColor(isChecked ? Color.accentColor : Color.secondary)
            .onTapGesture(perform: onTap)
    }
}

#Preview {
    CheckboxView(isChecked: true) {}
}
