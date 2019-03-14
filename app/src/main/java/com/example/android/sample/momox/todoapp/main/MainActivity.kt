package com.example.android.sample.momox.todoapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.sample.momox.todoapp.Injection
import com.example.android.sample.momox.todoapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tasksFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as ToDoListFragment? ?: ToDoListFragment.newInstance().also {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.contentFrame, it)
                }.commit()
        }

        ToDoListPresenter(Injection.provideTasksRepository(applicationContext),
                tasksFragment).apply {
        }
    }
}
