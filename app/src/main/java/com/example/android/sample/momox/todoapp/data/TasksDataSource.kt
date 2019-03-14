package com.example.android.sample.momox.todoapp.data

interface TasksDataSource {

    interface LoadTasksCallback {

        fun onTasksLoaded(tasks: List<Task>)
    }

    interface GetTaskCallback {

        fun onTaskLoaded(task: Task)
    }
    fun getTask(taskId: String, callback: GetTaskCallback)

    fun getTasks(callback: LoadTasksCallback)

    fun saveTask(task: Task)

    fun deleteAllTasks()

    fun deleteTask(taskId: String)
}