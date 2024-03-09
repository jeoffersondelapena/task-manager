//
//  DateTimeManager.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

struct DateTimeManager {
    private static var dateFormatter: DateFormatter = {
        let dateFormatter = DateFormatter()
        dateFormatter.locale = Locale(identifier: "en_US_POSIX")
        return dateFormatter
    }()

    static func yyyyMmDdToDate(_ dateString: String) -> Date? {
        dateFormatter.dateFormat = "yyyy-MM-dd"
        return dateFormatter.date(from: dateString)
    }

    static func dateToMmmmDdYyyy(_ date: Date) -> String {
        dateFormatter.dateStyle = .long
        return dateFormatter.string(from: date)
    }
}
