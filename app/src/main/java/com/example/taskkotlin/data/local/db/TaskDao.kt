package com.example.taskkotlin.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskkotlin.data.model.Home

@Dao
interface TaskDao {

    @Query("SELECT * FROM name_list ORDER BY title ASC")
    fun getAll(): List<Home>

    @Insert
    fun insert(home: Home)

    @Delete
    fun delete(home: Home)

    @Update
    fun update(home: Home)
}