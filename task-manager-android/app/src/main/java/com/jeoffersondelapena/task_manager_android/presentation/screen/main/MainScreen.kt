package com.jeoffersondelapena.task_manager_android.presentation.screen.main

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListState
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListViewModel
import com.jeoffersondelapena.task_manager_android.presentation.res.ui.theme.TaskmanagerandroidTheme
import com.jeoffersondelapena.task_manager_android.presentation.reusable_view.AddEditTaskModalBottomSheet
import com.jeoffersondelapena.task_manager_android.presentation.reusable_view.dialog.ErrorDialog
import com.jeoffersondelapena.task_manager_android.presentation.screen.main.bottom_nav_bar.BottomBar
import com.jeoffersondelapena.task_manager_android.presentation.screen.main.bottom_nav_bar.BottomNavGraph
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: TasksListViewModel = viewModel()) {
    val navController = rememberNavController()

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    viewModel.getTasks()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
        content = { padding ->
            BottomNavGraph(
                viewModel = viewModel,
                navController = navController,
                modifier = Modifier.padding(padding)
            )

            if (viewModel.state.activeModalBottomSheet.value != null) {
                ModalBottomSheet(
                    onDismissRequest = {
                        viewModel.state.activeModalBottomSheet.value = null
                    },
                    sheetState = sheetState,
                ) {
                    AddEditTaskModalBottomSheet(
                        viewModel = viewModel,
                        type = viewModel.state.activeModalBottomSheet.value
                            ?: TasksListState.ModalBottomSheetType.Add
                    )
                }
            }

            when (val dialog = viewModel.state.activeDialog.value) {
                is TasksListState.DialogType.Error -> {
                    ErrorDialog(
                        onDismissRequest = { viewModel.state.activeDialog.value = null },
                        dialogTitle = dialog.exception.message,
                    )
                }
                else -> {}
            }
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
