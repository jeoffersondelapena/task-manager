package com.jeoffersondelapena.task_manager_android.presentation.reusable_view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeoffersondelapena.task_manager_android.domain.model.Task
import com.jeoffersondelapena.task_manager_android.domain.util.helper.DateManager

@Composable
fun TaskItem(task: Task) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        Column(Modifier.weight(1f)) {
            Text(task.title)

            task.description?.let { description ->
                Text(description)
            }

            task.deadline?.let { deadline ->
                Text(DateManager.format(deadline, "MM-dd-yyyy"))
            }
        }
        
        Checkbox(checked = task.isCompleted, onCheckedChange = {})
    }
}