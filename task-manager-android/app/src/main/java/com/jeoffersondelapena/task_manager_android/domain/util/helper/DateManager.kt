package com.jeoffersondelapena.task_manager_android.domain.util.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateManager {
    const val SQLITE_DATE_FORMAT = "yyyy-MM-dd"
    const val PRESENTATION_DATE_FORMAT = "MMM dd, yyyy"

    fun format(date: Date, format: String): String {
        return SimpleDateFormat(format, Locale.getDefault()).format(date)
    }

    fun parse(dateString: String, format: String): Date? {
        return try {
            SimpleDateFormat(format, Locale.getDefault()).parse(dateString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}