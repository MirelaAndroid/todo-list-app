package com.example.android.sample.momox.todoapp.main

import com.example.android.sample.momox.todoapp.BasePresenter
import com.example.android.sample.momox.todoapp.BaseView
import com.example.android.sample.momox.todoapp.data.Task

interface ToDoListContract {

    interface View : BaseView<Presenter> {
        fun addTask()

        fun showDetails(taskId: String)

        fun showTasks(tasks: List<Task>)
    }

    interface Presenter : BasePresenter {
        fun addTask()

        fun deleteTask(taskId: String)

        fun openTask(requestedTask: Task)

        fun showTasks()

    }
}
