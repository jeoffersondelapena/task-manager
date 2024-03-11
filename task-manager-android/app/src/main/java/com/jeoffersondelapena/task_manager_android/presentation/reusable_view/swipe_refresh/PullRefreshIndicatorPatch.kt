package com.jeoffersondelapena.task_manager_android.presentation.reusable_view.swipe_refresh

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
internal fun surfaceColorAtElevation(color: Color, elevation: Dp): Color =
    when (color) {
        MaterialTheme.colorScheme.surface ->
            MaterialTheme.colorScheme.surfaceColorAtElevation(elevation)
        else -> color
    }
