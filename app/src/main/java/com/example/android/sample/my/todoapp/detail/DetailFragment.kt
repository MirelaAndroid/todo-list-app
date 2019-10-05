package com.example.android.sample.my.todoapp.detail

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.android.sample.my.todoapp.R
import com.example.android.sample.my.todoapp.edit.EditActivity
import com.example.android.sample.my.todoapp.edit.EditFragment
import org.jetbrains.anko.find

class DetailFragment : Fragment(), DetailContract.View {

    private lateinit var taskTitle: TextView
    private lateinit var taskDescription: TextView
    private lateinit var taskTime: TextView

    override lateinit var presenter: DetailContract.Presenter

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)

        with(root) {
            taskTitle = find(R.id.tv_detail_title)
            taskDescription = find(R.id.tv_detail_description)
            taskTime = find(R.id.tv_detail_time)
        }

        activity?.find<FloatingActionButton>(R.id.fab_edit)?.setOnClickListener {
            presenter.editTask()
        }

        return root
    }

    override fun showEditTask(taskId: String) {
        val intent = Intent(context, EditActivity::class.java)
        intent.putExtra(EditFragment.ARGUMENT_EDIT_TASK_ID, taskId)
        startActivity(intent)
    }

    override fun showTask(title: String, description: String, time: String) {
        taskTitle.text = title
        taskDescription.text = description
        taskTime.text = time
    }

    companion object {

        private val ARGUMENT_TASK_ID = "TASK_ID"

        fun newInstance(taskId: String?) =
                DetailFragment().apply {
                    arguments = Bundle().apply { putString(ARGUMENT_TASK_ID, taskId) }
                }
    }
}
