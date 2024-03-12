package com.jeoffersondelapena.task_manager_android.data.local.service.base

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class BaseLocalService(context: Context) : SQLiteOpenHelper(
    context,
    "task_manager.db",
    null,
    1
) {
    protected companion object {
        const val TABLE_TASK = "Task"
        const val COLUMN_ID = "ID"
        const val COLUMN_TITLE = "Title"
        const val COLUMN_DESCRIPTION = "Description"
        const val COLUMN_DEADLINE = "Deadline"
        const val COLUMN_IS_COMPLETED = "IsCompleted"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement = "CREATE TABLE $TABLE_TASK ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITLE TEXT, $COLUMN_DESCRIPTION TEXT, $COLUMN_DEADLINE DATE, $COLUMN_IS_COMPLETED BOOL)"
        db?.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}
