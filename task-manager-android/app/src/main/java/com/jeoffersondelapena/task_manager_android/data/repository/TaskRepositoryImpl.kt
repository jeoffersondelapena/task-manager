package com.jeoffersondelapena.task_manager_android.data.repository

import com.jeoffersondelapena.task_manager_android.data.local.service.TaskLocalService
import com.jeoffersondelapena.task_manager_android.data.remote.service.TaskRemoteService
import com.jeoffersondelapena.task_manager_android.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskRemoteService: TaskRemoteService,
    private val taskLocalService: TaskLocalService,
) : TaskRepository {
    override fun getTasks() {
        taskLocalService.getTasks()
    }
}
