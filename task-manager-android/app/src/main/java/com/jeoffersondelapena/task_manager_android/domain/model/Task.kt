package com.jeoffersondelapena.task_manager_android.domain.model

import java.util.Date

data class Task(
    val id: Int?,
    val title: String,
    val description: String?,
    val deadline: Date?,
    val isCompleted: Boolean,
)
