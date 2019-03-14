package com.example.android.sample.momox.todoapp.detail

import com.example.android.sample.momox.todoapp.BasePresenter
import com.example.android.sample.momox.todoapp.BaseView

interface DetailContract {

    interface View : BaseView<Presenter> {

        fun showTask(title: String, description: String, time: String)

        fun showEditTask(taskId: String)
    }

    interface Presenter : BasePresenter {

        fun editTask()
    }
}
