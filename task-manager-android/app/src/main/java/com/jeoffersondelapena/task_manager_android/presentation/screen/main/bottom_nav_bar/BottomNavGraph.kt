package com.jeoffersondelapena.task_manager_android.presentation.screen.main.bottom_nav_bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListViewModel
import com.jeoffersondelapena.task_manager_android.presentation.screen.completed_tasks_list_screen.CompletedTasksListScreen
import com.jeoffersondelapena.task_manager_android.presentation.screen.tasks_list_screen.TasksListScreen

@Composable
fun BottomNavGraph(
    viewModel: TasksListViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    ) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.TasksList.route,
        modifier = modifier,
    ) {
        composable(route = BottomBarScreen.TasksList.route) {
            TasksListScreen(viewModel = viewModel)
        }

        composable(route = BottomBarScreen.CompletedTasksList.route) {
            CompletedTasksListScreen(viewModel = viewModel)
        }
    }
}
