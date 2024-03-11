package com.jeoffersondelapena.task_manager_android.presentation.screen.main.bottom_nav_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    data object TasksList: BottomBarScreen(
        route = "tasksList",
        title = "Tasks",
        icon = Icons.Default.List
    )

    data object CompletedTasksList: BottomBarScreen(
        route = "completedTasksList",
        title = "Completed Tasks",
        icon = Icons.Default.CheckCircle
    )
}
