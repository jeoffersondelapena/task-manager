package com.jeoffersondelapena.task_manager_android.domain.repository

import com.jeoffersondelapena.task_manager_android.domain.model.Task
import com.jeoffersondelapena.task_manager_android.domain.util.helper.TaskManagerException
import com.jeoffersondelapena.task_manager_android.domain.util.helper.TaskManagerResult

interface TaskRepository {
    fun getTasks(): TaskManagerResult<List<Task>, TaskManagerException>
    fun addTask(task: Task): TaskManagerResult<Unit, TaskManagerException>
    fun deleteTask(task: Task): TaskManagerResult<Unit, TaskManagerException>
}
