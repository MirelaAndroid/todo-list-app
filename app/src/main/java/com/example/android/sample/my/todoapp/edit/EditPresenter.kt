package com.example.android.sample.my.todoapp.edit

import com.example.android.sample.my.todoapp.data.Task
import com.example.android.sample.my.todoapp.data.TasksDataSource

class EditPresenter(private val taskId: String?, private val tasksRepository: TasksDataSource,
                    private val addTaskView: AddEditTaskContract.View) :
        AddEditTaskContract.Presenter, TasksDataSource.GetTaskCallback {

    init {
        addTaskView.presenter = this
    }

    override fun start() {
        if (taskId != null) {
            populateTask()
        }
    }

    override fun saveTask(title: String, description: String, time: String) {
        if (taskId == null) {
            createTask(title, description, time)
        } else {
            updateTask(title, description, time)
        }
    }

    override fun populateTask() {
        if (taskId != null) {
            tasksRepository.getTask(taskId, this)
        }
    }

    override fun onTaskLoaded(task: Task) {
        addTaskView.setTask(task.title, task.description, task.time)
    }

    private fun createTask(title: String, description: String, time: String) {
        val newTask = Task(title, description, time)
        if (!newTask.isEmpty) {
            tasksRepository.saveTask(newTask)
            addTaskView.showList()
        }
    }

    private fun updateTask(title: String, description: String, time: String) {
        if (taskId != null) {
            tasksRepository.saveTask(Task(title, description, time, taskId))
            addTaskView.showList()
        }
    }
}
