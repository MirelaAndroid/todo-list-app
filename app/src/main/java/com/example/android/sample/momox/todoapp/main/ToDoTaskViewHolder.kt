package com.example.android.sample.momox.todoapp.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.sample.momox.todoapp.R
import org.jetbrains.anko.find

class ToDoTaskViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    var title: TextView = view.find(R.id.tv_title)
    var description: TextView = view.find(R.id.tv_description)
    var time: TextView = view.find(R.id.tv_time)
    lateinit var taskId: String
}