package com.jeoffersondelapena.task_manager_android.presentation.reusable_view.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ErrorDialog(
    onDismissRequest: () -> Unit,
    dialogTitle: String?,
) {
    AlertDialog(
        icon = {
            Icon(Icons.Default.Warning, contentDescription = "Alert dialog")
        },
        title = {
            Text(text = dialogTitle ?: "An error has occurred")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("OK")
            }
        },
    )
}
