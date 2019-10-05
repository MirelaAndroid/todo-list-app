package com.example.android.sample.my.todoapp.edit

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android.sample.my.todoapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

import org.jetbrains.anko.find
import org.jetbrains.anko.onClick

class EditFragment : Fragment(), AddEditTaskContract.View, DatePickerDialog.OnDateSetListener {

    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var time: Button

    override lateinit var presenter: AddEditTaskContract.Presenter

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        time.text = p1.toString() + "/" + p2.toString() + "/" + p3.toString()
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.findViewById<FloatingActionButton>(R.id.fab_done)?.apply {
            setImageResource(R.drawable.ic_done)
            setOnClickListener {
                presenter.saveTask(title.text.toString(), description.text.toString(), time.text.toString())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_edit, container, false)
        with(root) {
            title = find(R.id.add_task_title)
            description = find(R.id.add_task_description)
            time = find(R.id.task_date_button)

            val datePickerDialog = DatePickerDialog(
                    context, this@EditFragment, 2019, 1, 1)
            time.onClick { datePickerDialog.show() }
        }
        return root
    }

    override fun showList() {
        activity?.apply {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun setTask(title: String, description: String, time: String) {
        this.title.text = title
        this.description.text = description
        this.time.text = time
    }

    companion object {
        const val ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID"

        fun newInstance(taskId: String?) =
                EditFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARGUMENT_EDIT_TASK_ID, taskId)
                    }
                }
    }
}
