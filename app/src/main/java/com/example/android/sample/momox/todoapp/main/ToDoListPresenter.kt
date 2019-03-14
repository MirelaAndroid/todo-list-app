package com.example.android.sample.momox.todoapp.main

import com.example.android.sample.momox.todoapp.data.Task
import com.example.android.sample.momox.todoapp.data.TasksDataSource
import com.example.android.sample.momox.todoapp.data.TasksRepository
import java.util.ArrayList

class ToDoListPresenter(private val tasksRepository: TasksRepository, private val toDoListView: ToDoListContract.View)
    : ToDoListContract.Presenter {

    init {
        toDoListView.presenter = this
    }

    override fun start() {
        showTasks()
    }

    override fun showTasks() {
        tasksRepository.getTasks(object : TasksDataSource.LoadTasksCallback {
            override fun onTasksLoaded(tasks: List<Task>) {
                val tasksToShow = ArrayList<Task>()

                for (task in tasks) {
                    tasksToShow.add(task)
                }

                processTasks(tasksToShow)
            }
        })
    }

    private fun processTasks(tasks: List<Task>) {
        toDoListView.showTasks(tasks)
    }

    override fun addTask() {
        toDoListView.addTask()
    }

    override fun openTask(requestedTask: Task) {
        toDoListView.showDetails(requestedTask.id)
    }

    override fun deleteTask(taskId: String) {
        tasksRepository.deleteTask(taskId)
    }
}
