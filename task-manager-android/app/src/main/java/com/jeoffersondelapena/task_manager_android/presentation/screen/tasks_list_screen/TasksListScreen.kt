package com.jeoffersondelapena.task_manager_android.presentation.screen.tasks_list_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeoffersondelapena.task_manager_android.presentation.res.ui.theme.TaskmanagerandroidTheme

@Composable
fun TasksListScreen(modifier: Modifier = Modifier, viewModel: TasksListViewModel = viewModel()) {
    viewModel.getTasks()

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Hello, Hilt!")
    }
}

@Preview(showBackground = true)
@Composable
fun TasksListScreenPreview() {
    TaskmanagerandroidTheme {
        TasksListScreen(viewModel = TasksListViewModel.sample)
    }
}
