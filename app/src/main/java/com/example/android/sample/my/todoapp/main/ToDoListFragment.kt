package com.example.android.sample.my.todoapp.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.android.sample.my.todoapp.R
import com.example.android.sample.my.todoapp.edit.EditActivity
import com.example.android.sample.my.todoapp.data.Task
import com.example.android.sample.my.todoapp.detail.DetailActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.find
import java.util.ArrayList

class ToDoListFragment : Fragment(), ToDoListContract.View {

    override lateinit var presenter: ToDoListContract.Presenter

    private val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            presenter.deleteTask(listAdapter.tasks[position].id)
        }
    }

    private val clickListener: ToDoTaskListener = object : ToDoTaskListener {
        override fun onClick(clickedTask: Task) {
            presenter.openTask(clickedTask)
        }

    }

    private val listAdapter = ToDoTaskAdapter(ArrayList(0), clickListener)

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.todolist_fragment, container, false)

        val todoList = view.find<RecyclerView>(R.id.tasks_list)
        todoList.layoutManager = GridLayoutManager(context,1)
        todoList.adapter = listAdapter

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(todoList)

        requireActivity().find<FloatingActionButton>(R.id.fab_add).apply {
            setImageResource(R.drawable.ic_add)
            setOnClickListener { presenter.addTask() }
        }

        return view
    }

    override fun showTasks(tasks: List<Task>) {
        listAdapter.tasks = tasks
    }

    override fun addTask() {
        val intent = Intent(context, EditActivity::class.java)
        startActivityForResult(intent, EditActivity.REQUEST_ADD_TASK)
    }

    override fun showDetails(taskId: String) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_ID, taskId)
        }
        startActivity(intent)
    }

    companion object {
        fun newInstance() = ToDoListFragment()
    }

}


