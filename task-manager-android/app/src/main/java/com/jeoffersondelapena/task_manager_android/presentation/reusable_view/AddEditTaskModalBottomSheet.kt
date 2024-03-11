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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.jeoffersondelapena.task_manager_android.domain.model.Task
import com.jeoffersondelapena.task_manager_android.domain.util.helper.DateManager
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListState
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskModalBottomSheet(
    viewModel: TasksListViewModel,
    type: TasksListState.ModalBottomSheetType
) {
    var title by remember { mutableStateOf("") }
    var titleErrorMessage by remember { mutableStateOf("") }

    var addDeadline by remember { mutableStateOf(false) }
    var isShowingDatePicker by remember { mutableStateOf(false) }
    var deadline by remember { mutableStateOf(Date()) }

    var description by remember { mutableStateOf("") }

    var isEditing by remember { mutableStateOf(false) }

    val isDisabledTextFields = type is TasksListState.ModalBottomSheetType.Modify && !isEditing
    val fieldsOpacity = if (isDisabledTextFields) .5f else 1f

    val isDisabledDatePicker = (type is TasksListState.ModalBottomSheetType.Modify && !isEditing) || !addDeadline
    val datePickerOpacity = if (isDisabledDatePicker) .5f else 1f

    LaunchedEffect(Unit) {
        if (type is TasksListState.ModalBottomSheetType.Modify) {
            title = type.task.title
            addDeadline = type.task.deadline != null
            deadline = type.task.deadline ?: Date()
            description = type.task.description ?: ""
        }
    }

    fun validateTask(): Task? {
        if (title.isBlank()) {
            titleErrorMessage = "Title should not be blank"
            return null
        }
        when (type) {
            is TasksListState.ModalBottomSheetType.Add -> {
                return Task(
                    id = null,
                    title = title,
                    description =  if (description.isBlank()) null else description,
                    deadline = if (!addDeadline) null else deadline,
                    isCompleted = false,
                )
            }
            is TasksListState.ModalBottomSheetType.Modify -> {
                return Task(
                    id = type.task.id,
                    title = title,
                    description =  if (description.isBlank()) null else description,
                    deadline = if (!addDeadline) null else deadline,
                    isCompleted = type.task.isCompleted,
                )
            }
        }
    }

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
                titleErrorMessage = ""
            },
            enabled = !isDisabledTextFields,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(fieldsOpacity),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.alpha(fieldsOpacity)
        ) {
            Text("Add deadline", modifier = Modifier.weight(1f))

            Switch(
                checked = addDeadline,
                onCheckedChange = {
                    addDeadline = it
                },
                enabled = !isDisabledTextFields,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.alpha(datePickerOpacity)
        ) {
            Text("Deadline", modifier = Modifier.weight(1f))

            Button(
                content = {
                    Text(DateManager.format(deadline, DateManager.PRESENTATION_DATE_FORMAT))
                },
                onClick = {
                    isShowingDatePicker = true
                },
                enabled = !isDisabledDatePicker
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
            enabled = !isDisabledTextFields,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(fieldsOpacity),
        )

        when (type) {
            is TasksListState.ModalBottomSheetType.Add -> {
                Button(
                    content = {
                        Text("Save")
                    },
                    onClick = {
                        viewModel.addTask(validateTask() ?: return@Button)
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            is TasksListState.ModalBottomSheetType.Modify -> {
                if (!isEditing) {
                    Button(
                        content = {
                            Text(if (type.task.isCompleted) "Restart task" else "Complete task")
                        },
                        onClick = {
                            viewModel.toggleTaskCompletion(type.task)
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (!isEditing) {
                        Button(
                            content = {
                                Text("Edit")
                            },
                            onClick = {
                                isEditing = true
                            },
                            modifier = Modifier.weight(1f),
                        )

                    } else {
                        Button(
                            content = {
                                Text("Save")
                            },
                            onClick = {
                                viewModel.editTask(validateTask() ?: return@Button)
                            },
                            modifier = Modifier.weight(1f),
                        )
                    }

                    Button(
                        content = {
                            Text("Delete")
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color.Red
                        ),
                        onClick = {
                            viewModel.deleteTask(type.task)
                        },
                        modifier = Modifier.weight(1f),
                    )
                }
            }
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
