//
//  String+Extensions.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/10/24.
//

import Foundation

extension String {
    func isBlank() -> Bool {
        self.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty
    }
    
    func isNotBlank() -> Bool {
        !isBlank()
    }
}

extension Optional<String> {
    func isNullOrBlank() -> Bool {
        guard let value = self else {
            return true
        }
        return value.isBlank()
    }
    
    func isNotNullNorBlank() -> Bool {
        !isNullOrBlank()
    }
}
