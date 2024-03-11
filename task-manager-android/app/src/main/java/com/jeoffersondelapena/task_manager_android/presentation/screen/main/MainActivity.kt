package com.jeoffersondelapena.task_manager_android.presentation.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jeoffersondelapena.task_manager_android.presentation.res.ui.theme.TaskmanagerandroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskmanagerandroidTheme(darkTheme = true) {
                MainScreen()
            }
        }
    }
}
