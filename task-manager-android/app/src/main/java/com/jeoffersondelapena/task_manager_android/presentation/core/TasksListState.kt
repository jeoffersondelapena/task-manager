package com.jeoffersondelapena.task_manager_android.presentation.core

import com.jeoffersondelapena.task_manager_android.domain.model.Task

object TasksListState {
    var tasks: List<Task> = listOf()
    val completedTasks: List<Task>
        get() {
            return tasks.filter { task ->
                task.isCompleted
            }
        }
    var isLoading = false
}