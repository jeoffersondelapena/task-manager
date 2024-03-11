package com.jeoffersondelapena.task_manager_android.presentation.reusable_view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.jeoffersondelapena.task_manager_android.domain.model.Task
import com.jeoffersondelapena.task_manager_android.domain.util.helper.DateManager
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListState
import com.jeoffersondelapena.task_manager_android.presentation.core.TasksListViewModel

@Composable
fun TaskItem(viewModel: TasksListViewModel, task: Task, allowStrikethrough: Boolean = true) {
    val showStrikethrough = allowStrikethrough && task.isCompleted

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                viewModel.state.activeModalBottomSheet.value =
                    TasksListState.ModalBottomSheetType.Modify(task)
            }
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier =  Modifier.weight(1f)) {
            Text(
                task.title,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    textDecoration = if (showStrikethrough) TextDecoration.LineThrough else TextDecoration.None
                ),
            )

            task.description?.let { description ->
                Text(
                    description,
                    fontWeight = FontWeight.Light,
                    style = TextStyle(
                        textDecoration = if (showStrikethrough) TextDecoration.LineThrough else TextDecoration.None
                    ),
                )
            }

            task.deadline?.let { deadline ->
                Text(
                    "Deadline: ${DateManager.format(deadline, DateManager.PRESENTATION_DATE_FORMAT)}",
                    style = TextStyle(
                        textDecoration = if (showStrikethrough) TextDecoration.LineThrough else TextDecoration.None
                    ),
                )
            }
        }
        
        Spacer(modifier = Modifier.width(8.dp))
        
        Checkbox(checked = task.isCompleted, onCheckedChange = {})
    }
}