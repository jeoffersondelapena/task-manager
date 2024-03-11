package com.jeoffersondelapena.task_manager_android.data.remote.service

import com.jeoffersondelapena.task_manager_android.data.remote.dto.TaskRemoteDto
import com.jeoffersondelapena.task_manager_android.domain.util.helper.TaskManagerException
import com.jeoffersondelapena.task_manager_android.domain.util.helper.TaskManagerResult

class TaskRemoteService {
    fun getTasks(): TaskManagerResult<List<TaskRemoteDto>, TaskManagerException> {
        // Network logic here...
        return TaskManagerResult.Success(listOf())
    }

    fun addTask(taskRemoteDto: TaskRemoteDto): TaskManagerResult<Unit, TaskManagerException> {
        // Network logic here...
        return TaskManagerResult.Success(Unit)
    }

    fun editTask(taskRemoteDto: TaskRemoteDto): TaskManagerResult<Unit, TaskManagerException> {
        // Network logic here...
        return TaskManagerResult.Success(Unit)
    }

    fun toggleTaskCompletion(taskRemoteDto: TaskRemoteDto): TaskManagerResult<Unit, TaskManagerException> {
        // Network logic here...
        return TaskManagerResult.Success(Unit)
    }

    fun deleteTask(taskRemoteDto: TaskRemoteDto): TaskManagerResult<Unit, TaskManagerException> {
        // Network logic here...
        return TaskManagerResult.Success(Unit)
    }
}
