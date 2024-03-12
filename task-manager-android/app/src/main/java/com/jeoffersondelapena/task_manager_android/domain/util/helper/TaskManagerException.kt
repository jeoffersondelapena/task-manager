package com.jeoffersondelapena.task_manager_android.domain.util.helper

sealed class TaskManagerException(message: String) : Exception(message) {
    data object FailedToAddTaskException : TaskManagerException("Failed to add task") {
        private fun readResolve(): Any = FailedToAddTaskException
    }

    data object FailedToEditTaskException : TaskManagerException("Failed to delete task") {
        private fun readResolve(): Any = FailedToAddTaskException
    }

    data object FailedToToggleTaskCompletionException : TaskManagerException("Failed to delete task") {
        private fun readResolve(): Any = FailedToAddTaskException
    }

    data object FailedToDeleteTaskException : TaskManagerException("Failed to delete task") {
        private fun readResolve(): Any = FailedToAddTaskException
    }
}
