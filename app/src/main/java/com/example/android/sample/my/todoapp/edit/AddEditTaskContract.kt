package com.example.android.sample.my.todoapp.edit

import com.example.android.sample.my.todoapp.BasePresenter
import com.example.android.sample.my.todoapp.BaseView

interface AddEditTaskContract {

    interface View : BaseView<Presenter> {

        fun showList()

        fun setTask(title: String, description: String, time: String)
    }

    interface Presenter : BasePresenter {

        fun saveTask(title: String, description: String, time: String)

        fun populateTask()
    }
}
