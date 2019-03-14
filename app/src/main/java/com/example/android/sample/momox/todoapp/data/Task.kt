package com.example.android.sample.momox.todoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "tasks")
data class Task @JvmOverloads constructor(
        @ColumnInfo(name = "title") var title: String = "",
        @ColumnInfo(name = "description") var  description: String = "",
        @ColumnInfo(name = "time") var  time: String = "",
        @PrimaryKey @ColumnInfo(name = "entryid") var id: String = UUID.randomUUID().toString()) {

    val isEmpty
        get() = title.isEmpty() && description.isEmpty() && time.isEmpty()
}