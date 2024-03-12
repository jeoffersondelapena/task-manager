package com.jeoffersondelapena.task_manager_android.presentation.core

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.jeoffersondelapena.task_manager_android.domain.model.Task
import com.jeoffersondelapena.task_manager_android.domain.util.helper.TaskManagerException

object TasksListState {
    sealed class ModalBottomSheetType {
        data object Add : ModalBottomSheetType()

        data class Modify(val task: Task) : ModalBottomSheetType()
    }

    sealed class DialogType {
        data class DeleteConfirmation(val task: Task) : DialogType()

        data class Error(val exception: TaskManagerException) : DialogType()
    }

    var tasks: MutableState<List<Task>> = mutableStateOf(listOf())
    val completedTasks: List<Task>
        get() {
            return tasks.value.filter { task ->
                task.isCompleted
            }
        }

    var isLoading = false

    var activeModalBottomSheet: MutableState<ModalBottomSheetType?> = mutableStateOf(null)
    var activeDialog: MutableState<DialogType?> = mutableStateOf(null)
}