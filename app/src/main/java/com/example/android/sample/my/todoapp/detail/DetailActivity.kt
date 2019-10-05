package com.example.android.sample.my.todoapp.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils.replace
import com.example.android.sample.my.todoapp.Injection
import com.example.android.sample.my.todoapp.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)
        actionBar?.setHomeButtonEnabled(true)

        val taskId = intent.getStringExtra(EXTRA_ID)

        val taskDetailFragment = supportFragmentManager
                .findFragmentById(R.id.contentFrame) as DetailFragment? ?:
                DetailFragment.newInstance(taskId).also {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.contentFrame, it)
                    }.commit()
                }


        DetailPresenter(taskId, Injection.provideTasksRepository(applicationContext),
                taskDetailFragment)
    }

    companion object {
        const val EXTRA_ID = "TASK_ID"
    }
}
