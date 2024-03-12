package com.jeoffersondelapena.task_manager_android.data.repository

import com.jeoffersondelapena.task_manager_android.data.local.dto.TaskLocalDto
import com.jeoffersondelapena.task_manager_android.data.local.dto.toDomain
import com.jeoffersondelapena.task_manager_android.data.local.service.TaskLocalService
import com.jeoffersondelapena.task_manager_android.data.remote.dto.TaskRemoteDto
import com.jeoffersondelapena.task_manager_android.data.remote.dto.toDomain
import com.jeoffersondelapena.task_manager_android.data.remote.service.TaskRemoteService
import com.jeoffersondelapena.task_manager_android.domain.model.Task
import com.jeoffersondelapena.task_manager_android.domain.repository.TaskRepository
import com.jeoffersondelapena.task_manager_android.domain.util.helper.TaskManagerException
import com.jeoffersondelapena.task_manager_android.domain.util.helper.TaskManagerResult
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskRemoteService: TaskRemoteService,
    private val taskLocalService: TaskLocalService,
) : TaskRepository {
    override fun getTasks(): TaskManagerResult<List<Task>, TaskManagerException> {
        if (/* !hasInternetConnection */ true) {
            return when (val result = taskLocalService.getTasks()) {
                is TaskManagerResult.Success -> {
                    TaskManagerResult.Success(result.value.toDomain())
                }
                is TaskManagerResult.Failure -> {
                    TaskManagerResult.Failure(result.exception)
                }
            }
        } else {
            return when (val result = taskRemoteService.getTasks()) {
                is TaskManagerResult.Success -> {
                    val taskRemoteDtos = result.value
                    cacheTasks(taskRemoteDtos)
                    TaskManagerResult.Success(taskRemoteDtos.toDomain())
                }
                is TaskManagerResult.Failure -> {
                    TaskManagerResult.Failure(result.exception)
                }
            }
        }
    }

    override fun addTask(task: Task): TaskManagerResult<Unit, TaskManagerException> {
        if (/* !hasInternetConnection */ true) {
            return taskLocalService.addTask(TaskLocalDto.toData(task))
        } else {
            return taskRemoteService.addTask(TaskRemoteDto.toData(task))
        }
    }

    override fun editTask(task: Task): TaskManagerResult<Unit, TaskManagerException> {
        if (/* !hasInternetConnection */ true) {
            return taskLocalService.editTask(TaskLocalDto.toData(task))
        } else {
            return taskRemoteService.editTask(TaskRemoteDto.toData(task))
        }
    }

    override fun toggleTaskCompletion(task: Task): TaskManagerResult<Unit, TaskManagerException> {
        if (/* !hasInternetConnection */ true) {
            return taskLocalService.toggleTaskCompletion(TaskLocalDto.toData(task))
        } else {
            return taskRemoteService.toggleTaskCompletion(TaskRemoteDto.toData(task))
        }
    }

    override fun deleteTask(task: Task): TaskManagerResult<Unit, TaskManagerException> {
        if (/* !hasInternetConnection */ true) {
            return taskLocalService.deleteTask(TaskLocalDto.toData(task))
        } else {
            return taskRemoteService.deleteTask(TaskRemoteDto.toData(task))
        }
    }

    private fun cacheTasks(taskRemoteDtos: List<TaskRemoteDto>) {
        taskLocalService.addTask(TaskLocalDto.toData(taskRemoteDtos.toDomain()))
    }
}
