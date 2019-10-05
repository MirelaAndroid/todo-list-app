package com.example.android.sample.my.todoapp.main

import com.example.android.sample.my.todoapp.BasePresenter
import com.example.android.sample.my.todoapp.BaseView
import com.example.android.sample.my.todoapp.data.Task

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
