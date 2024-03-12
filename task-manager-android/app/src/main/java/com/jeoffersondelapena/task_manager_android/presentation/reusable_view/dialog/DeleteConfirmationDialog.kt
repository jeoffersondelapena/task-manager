package com.jeoffersondelapena.task_manager_android.presentation.reusable_view.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.jeoffersondelapena.task_manager_android.domain.model.Task

@Composable
fun DeleteConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    task: Task,
) {
    AlertDialog(
        icon = {
            Icon(Icons.Default.Warning, contentDescription = "Example Icon")
        },
        title = {
            Text("Are you sure you want to delete '${task.title}'?")
        },
        text = {
            Text("You cannot undo this action.")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}
