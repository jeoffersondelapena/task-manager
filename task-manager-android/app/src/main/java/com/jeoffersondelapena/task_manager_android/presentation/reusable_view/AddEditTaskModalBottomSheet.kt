package com.jeoffersondelapena.task_manager_android.presentation.reusable_view

import AppDatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jeoffersondelapena.task_manager_android.domain.util.helper.DateManager
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListState
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskModalBottomSheet(
    viewModel: TasksListViewModel,
    type: TasksListState.ModalBottomSheetType
) {
    var title by remember { mutableStateOf("") }
    var titleErrorMessage = ""

    var addDeadline by remember { mutableStateOf(true) }
    var isShowingDatePicker by remember { mutableStateOf(false) }
    var deadline by remember { mutableStateOf(Date()) }

    var description by remember { mutableStateOf("") }

    var isEditing by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
        if (viewModel.state.isLoading) {
            CircularProgressIndicator()
        }
        
        TextField(
            value = title,
            placeholder = {
                Text("Task title")
            },
            supportingText = {
                Text(titleErrorMessage)
            },
            isError = titleErrorMessage.isNotBlank(),
            onValueChange = { value ->
                title = value
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Add deadline", modifier = Modifier.weight(1f))

            Switch(
                checked = addDeadline,
                onCheckedChange = {
                    addDeadline = it
                }
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Deadline", modifier = Modifier.weight(1f))

            Button(
                content = {
                    Text(DateManager.format(deadline, DateManager.PRESENTATION_DATE_FORMAT))
                },
                onClick = {
                    isShowingDatePicker = !isShowingDatePicker
                }
            )
        }

        TextField(
            value = description,
            placeholder = {
                Text("Task description")
            },
            onValueChange = { value ->
                description = value
            },
            minLines = 5,
            maxLines = 10,
            modifier = Modifier.fillMaxWidth(),
        )

        Button(
            content = {
                Text("Save")
            },
            onClick = {

            },
            modifier = Modifier.fillMaxWidth(),
        )

        Button(
            content = {
                Text("Complete task")
            },
            onClick = {

            },
            modifier = Modifier.fillMaxWidth(),
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                content = {
                    Text("Edit")
                },
                onClick = {

                },
                modifier = Modifier.weight(1f),
            )

            Button(
                content = {
                    Text("Delete")
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Red
                ),
                onClick = {

                },
                modifier = Modifier.weight(1f),
            )
        }

        if (isShowingDatePicker) {
            ModalBottomSheet(
                onDismissRequest = {
                    isShowingDatePicker = false
                }
            ) {
                AppDatePicker(deadline) { date ->
                    deadline = date
                    isShowingDatePicker = false
                }
            }
        }
    }
}
