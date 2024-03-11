package com.jeoffersondelapena.task_manager_android.presentation.core

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jeoffersondelapena.task_manager_android.data.local.service.TaskLocalService
import com.jeoffersondelapena.task_manager_android.data.remote.service.TaskRemoteService
import com.jeoffersondelapena.task_manager_android.data.repository.TaskRepositoryImpl
import com.jeoffersondelapena.task_manager_android.domain.model.Task
import com.jeoffersondelapena.task_manager_android.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    fun getTasks() {
        repository.getTasks()
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
