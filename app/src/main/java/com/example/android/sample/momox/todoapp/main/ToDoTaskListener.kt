package com.example.android.sample.momox.todoapp.main

import com.example.android.sample.momox.todoapp.data.Task

interface ToDoTaskListener {

    fun onClick(clickedTask: Task)
}