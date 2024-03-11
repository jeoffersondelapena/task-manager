package com.jeoffersondelapena.task_manager_android.presentation.screen.tasks_list_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeoffersondelapena.task_manager_android.domain.model.Task
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListViewModel
import com.jeoffersondelapena.task_manager_android.presentation.screen.completed_tasks_list_screen.CompletedTasksListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksListScreen(viewModel: TasksListViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tasks List")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.addTask(
                        Task(
                            id = null,
                            title = "Hello, SQLite!",
                            description = null,
                            deadline = null,
                            isCompleted = false,
                        )
                    )
                },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Tasks List Screen")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TasksListScreenPreview() {
    CompletedTasksListScreen(viewModel = TasksListViewModel.sample(LocalContext.current))
}
