package com.example.android.sample.momox.todoapp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.sample.momox.todoapp.R
import com.example.android.sample.momox.todoapp.data.Task
import org.jetbrains.anko.onClick

class ToDoTaskAdapter(items: List<Task>, private val listener: ToDoTaskListener)
    : RecyclerView.Adapter<ToDoTaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoTaskViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return ToDoTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoTaskViewHolder, position: Int) {
        holder.taskId = tasks[position].id
        holder.title.text = tasks[position].title
        holder.description.text = tasks[position].description
        holder.time.text = tasks[position].time
        holder.itemView.onClick { listener.onClick(tasks[position]) }
    }

    var tasks: List<Task> = items
        set(tasks) {
            field = tasks
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int {
        return tasks.size
    }
}