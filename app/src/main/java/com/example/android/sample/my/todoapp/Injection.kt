package com.example.android.sample.my.todoapp

import android.content.Context
import com.example.android.sample.my.todoapp.data.TasksRepository
import com.example.android.sample.my.todoapp.data.TasksLocalDataSource
import com.example.android.sample.my.todoapp.data.ToDoDatabase
import com.example.android.sample.my.todoapp.util.AppExecutors

/**
 * Enables injection of mock implementations for
 * [TasksDataSource] at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
object Injection {
    fun provideTasksRepository(context: Context): TasksRepository {
        val database = ToDoDatabase.getInstance(context)
        return TasksRepository.getInstance(TasksLocalDataSource.getInstance(AppExecutors(), database.taskDao()))
    }
}