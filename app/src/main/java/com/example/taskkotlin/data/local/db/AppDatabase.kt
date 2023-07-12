package com.example.taskkotlin.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskkotlin.data.model.Home

@Database(entities = [Home::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao():TaskDao
}