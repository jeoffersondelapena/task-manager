//
//  BaseService.swift
//  task-manager-ios
//
//  Created by Jeofferson Dela Peña on 3/9/24.
//

import Foundation

class BaseService {
    func serviceCall<T>(_ callFunction: () throws -> Result<T, TaskManagerError>) -> Result<T, TaskManagerError> {
        do {
            return try callFunction()
        } catch let error {
            return .failure(.generic(error.localizedDescription))
        }
    }
}
