package com.example.android.sample.momox.todoapp.detail

import com.example.android.sample.momox.todoapp.data.Task
import com.example.android.sample.momox.todoapp.data.TasksDataSource
import com.example.android.sample.momox.todoapp.data.TasksRepository

class DetailPresenter(private val taskId: String, private val tasksRepository: TasksRepository,
                      private val detailView: DetailContract.View) : DetailContract.Presenter {

    init {
        detailView.presenter = this
    }

    override fun start() {
        openTask()
    }

    override fun editTask() {
        detailView.showEditTask(taskId)
    }

    private fun openTask() {
        tasksRepository.getTask(taskId, object : TasksDataSource.GetTaskCallback {
            override fun onTaskLoaded(task: Task) {
                showTask(task)
            }
        })
    }

    private fun showTask(task: Task) {
        with(detailView) {
                showTask(task.title, task.description, task.time)
        }
    }
}