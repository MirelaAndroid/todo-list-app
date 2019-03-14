package com.example.android.sample.momox.todoapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.android.sample.momox.todoapp.data.Task

@Dao interface TasksDao {
    @Query("SELECT * FROM Tasks") fun getTasks(): List<Task>
    @Query("SELECT * FROM Tasks WHERE entryid = :taskId") fun getTaskById(taskId: String): Task?
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertTask(task: Task)
    @Update fun updateTask(task: Task): Int
    @Query("DELETE FROM Tasks WHERE entryid = :taskId") fun deleteTaskById(taskId: String): Int
    @Query("DELETE FROM Tasks") fun deleteTasks()
}