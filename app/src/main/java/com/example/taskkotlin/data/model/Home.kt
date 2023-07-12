package com.example.taskkotlin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "name_list")
data class Home(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val desc: String
) : Serializable

//@ColumnInfo(name = "title_name")
