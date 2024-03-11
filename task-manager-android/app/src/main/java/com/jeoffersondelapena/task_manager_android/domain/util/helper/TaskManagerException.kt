package com.jeoffersondelapena.task_manager_android.domain.util.helper

sealed class TaskManagerException(message: String) : Exception(message) {
    data class GenericException(override val message: String) : TaskManagerException(message)

    data object TaskNotFoundException : TaskManagerException("Task not found") {
        private fun readResolve(): Any = TaskNotFoundException
    }

    data object FailedToAddTaskException : TaskManagerException("Failed to add task") {
        private fun readResolve(): Any = FailedToAddTaskException
    }
}
