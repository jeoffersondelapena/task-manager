package com.jeoffersondelapena.task_manager_android.presentation.core

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.jeoffersondelapena.task_manager_android.domain.model.Task

object TasksListState {
    sealed class ModalBottomSheetType {
        object Add : ModalBottomSheetType()

        data class Modify(val task: Task) : ModalBottomSheetType()
    }

    var tasks: List<Task> = listOf()
    val completedTasks: List<Task>
        get() {
            return tasks.filter { task ->
                task.isCompleted
            }
        }

    var isLoading = false

    var activeModalBottomSheet: MutableState<ModalBottomSheetType?> = mutableStateOf(null)
}