package com.example.android.sample.my.todoapp.data

import java.util.ArrayList
import java.util.LinkedHashMap

class TasksRepository(val tasksLocalDataSource: TasksDataSource) : TasksDataSource {

    var cachedTasks: LinkedHashMap<String, Task> = LinkedHashMap()

    override fun getTasks(callback: TasksDataSource.LoadTasksCallback) {
        if (cachedTasks.isNotEmpty()) {
            callback.onTasksLoaded(ArrayList(cachedTasks.values))
            return
        }

            tasksLocalDataSource.getTasks(object : TasksDataSource.LoadTasksCallback {
                override fun onTasksLoaded(tasks: List<Task>) {
                    refreshCache(tasks)
                    callback.onTasksLoaded(ArrayList(cachedTasks.values))
                }
            })

    }

    override fun saveTask(task: Task) {
        cacheAndPerform(task) {

            tasksLocalDataSource.saveTask(it)
        }
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
        val taskInCache = getTaskWithId(taskId)

        if (taskInCache != null) {
            callback.onTaskLoaded(taskInCache)
            return
        }

        tasksLocalDataSource.getTask(taskId, object : TasksDataSource.GetTaskCallback {
            override fun onTaskLoaded(task: Task) {
                cacheAndPerform(task) {
                    callback.onTaskLoaded(it)
                }
            }
        })
    }

    override fun deleteAllTasks() {
        tasksLocalDataSource.deleteAllTasks()
        cachedTasks.clear()
    }

    override fun deleteTask(taskId: String) {
        tasksLocalDataSource.deleteTask(taskId)
        cachedTasks.remove(taskId)
    }

    private fun getTasksFromRemoteDataSource(callback: TasksDataSource.LoadTasksCallback) {
        tasksLocalDataSource.getTasks(object : TasksDataSource.LoadTasksCallback {
            override fun onTasksLoaded(tasks: List<Task>) {
                refreshCache(tasks)
                refreshLocalDataSource(tasks)
                callback.onTasksLoaded(ArrayList(cachedTasks.values))
            }
        })
    }

    private fun refreshCache(tasks: List<Task>) {
        cachedTasks.clear()
        tasks.forEach {
            cacheAndPerform(it) {}
        }
    }

    private fun refreshLocalDataSource(tasks: List<Task>) {
        tasksLocalDataSource.deleteAllTasks()
        for (task in tasks) {
            tasksLocalDataSource.saveTask(task)
        }
    }

    private fun getTaskWithId(id: String) = cachedTasks[id]

    private inline fun cacheAndPerform(task: Task, perform: (Task) -> Unit) {
        val cachedTask = Task(task.title, task.description, task.time, task.id).apply {
        }
        cachedTasks.put(cachedTask.id, cachedTask)
        perform(cachedTask)
    }

    companion object {

        private var INSTANCE: TasksRepository? = null

        @JvmStatic fun getInstance(tasksLocalDataSource: TasksDataSource): TasksRepository {
            return INSTANCE
                    ?: TasksRepository(tasksLocalDataSource)
                    .apply { INSTANCE = this }
        }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }
}