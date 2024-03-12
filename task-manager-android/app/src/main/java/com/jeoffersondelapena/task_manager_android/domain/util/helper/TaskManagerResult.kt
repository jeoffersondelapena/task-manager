package com.jeoffersondelapena.task_manager_android.domain.util.helper

sealed class TaskManagerResult<T, U: TaskManagerException> {
    data class Success<T, U: TaskManagerException>(val value: T) : TaskManagerResult<T, U>()

    data class Failure<T, U: TaskManagerException>(val exception: U) : TaskManagerResult<T, U>()
}
