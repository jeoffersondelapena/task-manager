package com.jeoffersondelapena.task_manager_android.presentation.screen.main
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListViewModel
import com.jeoffersondelapena.task_manager_android.presentation.res.ui.theme.TaskmanagerandroidTheme
import com.jeoffersondelapena.task_manager_android.presentation.screen.main.bottom_nav_bar.BottomBar
import com.jeoffersondelapena.task_manager_android.presentation.screen.main.bottom_nav_bar.BottomNavGraph

@Composable
fun MainScreen(viewModel: TasksListViewModel = viewModel()) {
    val navController = rememberNavController()

    viewModel.getTasks()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        content = { padding ->
            BottomNavGraph(
                navController = navController,
                viewModel = viewModel,
                modifier = Modifier.padding(padding)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TaskmanagerandroidTheme {
        MainScreen(viewModel = TasksListViewModel.sample(LocalContext.current))
    }
}
