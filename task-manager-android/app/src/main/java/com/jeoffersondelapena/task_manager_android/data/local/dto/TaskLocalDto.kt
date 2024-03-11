package com.jeoffersondelapena.task_manager_android.data.local.dto

import com.jeoffersondelapena.task_manager_android.domain.model.Task
import com.jeoffersondelapena.task_manager_android.domain.util.helper.DateManager

data class TaskLocalDto(
    val id: Int?,
    val title: String,
    val description: String?,
    val deadline: String?,
    val isCompleted: Boolean,
) {
    fun toDomain(): Task {
        return Task(
            id = id,
            title = title,
            description = description,
            deadline = deadline?.let { deadline ->
                DateManager.parse(deadline, DateManager.SQLITE_DATE_FORMAT)
            },
            isCompleted = isCompleted,
        )
    }

    companion object {
        fun toData(task: Task): TaskLocalDto {
            return TaskLocalDto(
                id = task.id,
                title = task.title,
                description = task.description,
                deadline = task.deadline?.let { deadline ->
                    DateManager.format(deadline, DateManager.SQLITE_DATE_FORMAT)
                },
                isCompleted = task.isCompleted,
            )
        }

        fun toData(tasks: List<Task>): List<TaskLocalDto> {
            return tasks.map { task ->
                toData(task)
            }
        }
    }
}

fun List<TaskLocalDto>.toDomain(): List<Task> {
    return map { taskLocalDto ->
        taskLocalDto.toDomain()
    }
}
