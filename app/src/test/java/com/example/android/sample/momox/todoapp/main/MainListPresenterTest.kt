package com.example.android.sample.momox.todoapp.main

import com.example.android.sample.momox.todoapp.any
import com.example.android.sample.momox.todoapp.argumentCaptor
import com.example.android.sample.momox.todoapp.capture
import com.example.android.sample.momox.todoapp.data.Task
import com.example.android.sample.momox.todoapp.data.TasksDataSource.LoadTasksCallback
import com.example.android.sample.momox.todoapp.data.TasksRepository
import com.google.common.collect.Lists
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainListPresenterTest {

    @Mock
    private lateinit var tasksRepository: TasksRepository

    @Mock
    private lateinit var toDoListView: ToDoListContract.View

    @Captor
    private lateinit var loadTasksCallbackCaptor: ArgumentCaptor<LoadTasksCallback>

    private lateinit var toDoListPresenter: ToDoListPresenter

    private lateinit var tasks: MutableList<Task>

    private val ANY_STRING = "abc"

    @Before fun setupTasksPresenter() {
        MockitoAnnotations.initMocks(this)

        toDoListPresenter = ToDoListPresenter(tasksRepository, toDoListView)

        tasks = Lists.newArrayList(Task( ANY_STRING,  ANY_STRING),
                Task( ANY_STRING,  ANY_STRING).apply {})
    }

    @Test
    fun setPresenterToView() {
        toDoListPresenter = ToDoListPresenter(tasksRepository, toDoListView)

        verify(toDoListView).presenter = toDoListPresenter
    }

    @Test
    fun loadAllTasksFromRepositoryIntoView() {
        with(toDoListPresenter) {
            showTasks()
        }

        verify(tasksRepository).getTasks(capture(loadTasksCallbackCaptor))
        loadTasksCallbackCaptor.value.onTasksLoaded(tasks)

        val showTasksArgumentCaptor = argumentCaptor<List<Task>>()
        verify(toDoListView).showTasks(capture(showTasksArgumentCaptor))
        assertTrue(showTasksArgumentCaptor.value.size == tasks.size)
    }

    @Test
    fun clickOnFab() {
        toDoListPresenter.addTask()

        verify(toDoListView).addTask()
    }


    @Test
    fun deleteTask() {
        toDoListPresenter.deleteTask( ANY_STRING)

        verify(tasksRepository).deleteTask( ANY_STRING)
    }

    @Test
    fun onShowDetails() {
        val requestedTask = Task(ANY_STRING,  ANY_STRING)

        toDoListPresenter.openTask(requestedTask)

        verify(toDoListView).showDetails(any<String>())
    }
}
