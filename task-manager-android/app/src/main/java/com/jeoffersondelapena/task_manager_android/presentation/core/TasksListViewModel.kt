package com.jeoffersondelapena.task_manager_android.presentation.core

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jeoffersondelapena.task_manager_android.data.local.dto.toDomain
import com.jeoffersondelapena.task_manager_android.data.local.service.TaskLocalService
import com.jeoffersondelapena.task_manager_android.data.remote.service.TaskRemoteService
import com.jeoffersondelapena.task_manager_android.data.repository.TaskRepositoryImpl
import com.jeoffersondelapena.task_manager_android.domain.model.Task
import com.jeoffersondelapena.task_manager_android.domain.repository.TaskRepository
import com.jeoffersondelapena.task_manager_android.domain.util.helper.TaskManagerResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    val state = TasksListState

    fun getTasks(isFromSwipeRefresh: Boolean = false) {
        if (!isFromSwipeRefresh) {
            state.isLoading = true
        }

        when (val result = repository.getTasks()) {
            is TaskManagerResult.Success -> {
                state.tasks = result.value
            }
            is TaskManagerResult.Failure -> {
                // TODO(jeo)
            }
        }

        if (!isFromSwipeRefresh) {
            state.isLoading = false
        }
    }

    fun addTask(task: Task) {
        val result = repository.addTask(task)
        println("xyz: $result")
    }

    companion object {
        fun sample(context: Context): TasksListViewModel {
            return TasksListViewModel(
                TaskRepositoryImpl(
                    TaskRemoteService(),
                    TaskLocalService(context),
                )
            )
        }
    }
}
