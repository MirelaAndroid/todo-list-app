package com.example.android.sample.my.todoapp.main

import com.example.android.sample.my.todoapp.data.Task

interface ToDoTaskListener {

    fun onClick(clickedTask: Task)
}