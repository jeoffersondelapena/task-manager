package com.jeoffersondelapena.task_manager_android.presentation.screen.tasks_list_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListState
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListViewModel
import com.jeoffersondelapena.task_manager_android.presentation.reusable_view.TaskItem
import com.jeoffersondelapena.task_manager_android.presentation.reusable_view.pull_refresh.PullRefreshIndicator
import com.jeoffersondelapena.task_manager_android.presentation.reusable_view.pull_refresh.pullRefresh
import com.jeoffersondelapena.task_manager_android.presentation.reusable_view.pull_refresh.rememberPullRefreshState
import com.jeoffersondelapena.task_manager_android.presentation.screen.completed_tasks_list_screen.CompletedTasksListScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksListScreen(viewModel: TasksListViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Tasks List")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.state.activeModalBottomSheet.value = TasksListState.ModalBottomSheetType.Add
                },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        },
        content = { padding ->
            val refreshScope = rememberCoroutineScope()
            var refreshing by remember { mutableStateOf(false) }

            fun refresh() = refreshScope.launch {
                refreshing = true
                viewModel.getTasks(isFromSwipeRefresh = true)
                refreshing = false
            }

            val state = rememberPullRefreshState(refreshing, ::refresh)

            Column(modifier = Modifier.padding(padding)) {
                if (viewModel.state.isLoading) {
                    Box(contentAlignment = Alignment.Center, modifier =  Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }

                } else if (viewModel.state.tasks.value.isEmpty()) {
                    Box(contentAlignment = Alignment.Center, modifier =  Modifier.fillMaxSize()) {
                        Text("No tasks yet")
                    }

                } else {
                    Box(Modifier.pullRefresh(state)) {
                        LazyColumn(Modifier.fillMaxSize()) {
                            items(viewModel.state.tasks.value) { task ->
                                TaskItem(viewModel, task)
                            }
                        }

                        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TasksListScreenPreview() {
    CompletedTasksListScreen(viewModel = TasksListViewModel.sample(LocalContext.current))
}
