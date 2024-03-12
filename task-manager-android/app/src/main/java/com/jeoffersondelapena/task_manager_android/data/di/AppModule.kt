package com.jeoffersondelapena.task_manager_android.data.di

import android.content.Context
import com.jeoffersondelapena.task_manager_android.data.local.service.TaskLocalService
import com.jeoffersondelapena.task_manager_android.data.remote.service.TaskRemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTaskRemoteService(): TaskRemoteService {
        return TaskRemoteService()
    }

    @Provides
    @Singleton
    fun provideTaskLocalService(@ApplicationContext context: Context): TaskLocalService {
        return TaskLocalService(context)
    }
}
