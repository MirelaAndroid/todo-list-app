package com.example.android.sample.my.todoapp.edit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.sample.my.todoapp.Injection
import com.example.android.sample.my.todoapp.R

class EditActivity : AppCompatActivity() {

    private lateinit var editPresenter: EditPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val taskId = intent.getStringExtra(EditFragment.ARGUMENT_EDIT_TASK_ID)

        val addEditTaskFragment =
                supportFragmentManager.findFragmentById(R.id.contentFrame) as EditFragment?
                        ?: EditFragment.newInstance(taskId).also {
                            supportFragmentManager.beginTransaction().apply {
                                replace(R.id.contentFrame, it)
                            }.commit()
                }

        editPresenter = EditPresenter(taskId, Injection.provideTasksRepository(applicationContext),
                addEditTaskFragment)
    }

    companion object {
        const val REQUEST_ADD_TASK = 1
    }
}