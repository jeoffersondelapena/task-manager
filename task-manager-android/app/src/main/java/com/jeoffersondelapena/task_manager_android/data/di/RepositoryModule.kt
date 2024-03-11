package com.jeoffersondelapena.task_manager_android.data.di

import com.jeoffersondelapena.task_manager_android.data.repository.TaskRepositoryImpl
import com.jeoffersondelapena.task_manager_android.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTaskRepository(
        taskRepositoryImpl: TaskRepositoryImpl
    ): TaskRepository
}
