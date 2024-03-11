package com.jeoffersondelapena.task_manager_android.presentation.screen.tasks_list_screen

import androidx.lifecycle.ViewModel
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
}
