//
//  CheckboxView.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import SwiftUI

struct CheckboxView: View {
    @Binding var isChecked: Bool

    var body: some View {
        Image(systemName: isChecked ? "checkmark.square.fill" : "square")
            .foregroundColor(isChecked ? Color(UIColor.systemBlue) : Color.secondary)
            .onTapGesture {
                isChecked.toggle()
            }
    }
}

#Preview {
    CheckboxView(isChecked: .constant(true))
}
