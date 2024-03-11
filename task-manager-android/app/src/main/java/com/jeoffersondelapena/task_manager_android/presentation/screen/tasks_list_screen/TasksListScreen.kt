package com.jeoffersondelapena.task_manager_android.presentation.screen.tasks_list_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TasksListScreen(viewModel: TasksListViewModel = viewModel()) {
    viewModel.getTasks()
    Text("Hello, Hilt!")
}
