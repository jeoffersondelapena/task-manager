package com.jeoffersondelapena.task_manager_android.presentation.screen.completed_tasks_list_screen

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListState
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListViewModel
import com.jeoffersondelapena.task_manager_android.presentation.reusable_view.TaskItem
import com.jeoffersondelapena.task_manager_android.presentation.reusable_view.swipe_refresh.PullRefreshIndicator
import com.jeoffersondelapena.task_manager_android.presentation.reusable_view.swipe_refresh.pullRefresh
import com.jeoffersondelapena.task_manager_android.presentation.reusable_view.swipe_refresh.rememberPullRefreshState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompletedTasksListScreen(viewModel: TasksListViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Completed Tasks List")
                }
            )
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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(padding)
            ) {
                if (viewModel.state.isLoading) {
                    CircularProgressIndicator()
                }

                Box(Modifier.pullRefresh(state)) {
                    LazyColumn(Modifier.fillMaxSize()) {
                        items(viewModel.state.completedTasks) { task ->
                            TaskItem(viewModel, task, allowStrikethrough = false)
                        }
                    }

                    PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CompletedTasksListScreenPreview() {
    CompletedTasksListScreen(viewModel = TasksListViewModel.sample(LocalContext.current))
}
