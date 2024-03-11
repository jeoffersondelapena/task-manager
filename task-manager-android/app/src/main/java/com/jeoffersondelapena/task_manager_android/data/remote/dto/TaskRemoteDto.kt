package com.jeoffersondelapena.task_manager_android.data.remote.dto

import com.jeoffersondelapena.task_manager_android.domain.model.Task
import java.util.Date

data class TaskRemoteDto(
    val id: Int?,
    val title: String,
    val description: String?,
    val deadline: Date?,
    val isCompleted: Boolean,
) {
    fun toDomain(): Task {
        return Task(
            id = id,
            title = title,
            description = description,
            deadline = deadline,
            isCompleted = isCompleted,
        )
    }

    companion object {
        fun toData(task: Task): TaskRemoteDto {
            return TaskRemoteDto(
                id = task.id,
                title = task.title,
                description = task.description,
                deadline = task.deadline,
                isCompleted = task.isCompleted,
            )
        }

        fun toData(tasks: List<Task>): List<TaskRemoteDto> {
            return tasks.map { task ->
                toData(task)
            }
        }
    }
}

fun List<TaskRemoteDto>.toDomain(): List<Task> {
    return map { taskRemoteDto ->
        taskRemoteDto.toDomain()
    }
}
