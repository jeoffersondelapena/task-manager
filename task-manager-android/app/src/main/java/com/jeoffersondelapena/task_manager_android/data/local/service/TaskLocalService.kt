package com.jeoffersondelapena.task_manager_android.data.local.service

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.jeoffersondelapena.task_manager_android.data.local.dto.TaskLocalDto
import com.jeoffersondelapena.task_manager_android.data.local.service.base.BaseService
import com.jeoffersondelapena.task_manager_android.domain.util.helper.TaskManagerException
import com.jeoffersondelapena.task_manager_android.domain.util.helper.TaskManagerResult

class TaskLocalService(context: Context) : BaseService(context) {
    fun getTasks(): TaskManagerResult<List<TaskLocalDto>, TaskManagerException> {
        val taskLocalDtos: MutableList<TaskLocalDto> = mutableListOf()

        val queryString = "SELECT * FROM $TABLE_TASK ORDER BY $COLUMN_DEADLINE"
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery(queryString, null)

        if (cursor.moveToFirst()) {
            do {
                taskLocalDtos.add(
                    TaskLocalDto(
                        id = cursor.getInt(0),
                        title = cursor.getString(1),
                        description = cursor.getString(2),
                        deadline = cursor.getString(3),
                        isCompleted = cursor.getInt(4) == 1
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return TaskManagerResult.Success(taskLocalDtos)
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

    fun addTask(taskLocalDtos: List<TaskLocalDto>): TaskManagerResult<Unit, TaskManagerException> {
        var exception: TaskManagerException? = null

        val db = writableDatabase

        db.beginTransaction()

        try {
            taskLocalDtos.forEach { taskLocalDto ->
                if (addTask(taskLocalDto) is TaskManagerResult.Failure) {
                    exception = TaskManagerException.FailedToAddTaskException
                }
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        exception?.let {
            return TaskManagerResult.Failure(it)
        }

        return TaskManagerResult.Success(Unit)
    }
}
