package com.jeoffersondelapena.task_manager_android.data.local.service

import android.content.ContentValues
import android.content.Context
import com.jeoffersondelapena.task_manager_android.data.local.dto.TaskLocalDto
import com.jeoffersondelapena.task_manager_android.data.local.service.base.BaseService
import com.jeoffersondelapena.task_manager_android.domain.util.helper.TaskManagerException
import com.jeoffersondelapena.task_manager_android.domain.util.helper.TaskManagerResult

class TaskLocalService(context: Context) : BaseService(context) {
    fun getTasks(): TaskManagerResult<List<TaskLocalDto>, TaskManagerException> {
        return TaskManagerResult.Success(listOf())
    }

    fun addTask(taskLocalDto: TaskLocalDto): TaskManagerResult<Unit, TaskManagerException> {
        val contentValues = ContentValues()

        contentValues.apply {
            put(COLUMN_TITLE, taskLocalDto.title)
            put(COLUMN_DESCRIPTION, taskLocalDto.description)
            put(COLUMN_DEADLINE, taskLocalDto.deadline)
            put(COLUMN_IS_COMPLETED, taskLocalDto.isCompleted)
        }

        if (writableDatabase.insert(TABLE_TASK, null, contentValues) <= 0) {
            return TaskManagerResult.Failure(TaskManagerException.FailedToAddTaskException)
        }

        return TaskManagerResult.Success(Unit)
    }
}
