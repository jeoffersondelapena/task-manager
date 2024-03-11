//
//  DateManager.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

struct DateManager {
    private static var dateFormatter: DateFormatter = {
        let dateFormatter = DateFormatter()
        dateFormatter.locale = Locale(identifier: "en_US_POSIX")
        return dateFormatter
    }()
    
    static func format(date: Date, style: DateFormatter.Style = .long) -> String {
        dateFormatter.dateStyle = style
        return dateFormatter.string(from: date)
    }
    
    static func parse(dateString: String, format: String) -> Date? {
        dateFormatter.dateFormat = format
        return dateFormatter.date(from: dateString)
    }
}
