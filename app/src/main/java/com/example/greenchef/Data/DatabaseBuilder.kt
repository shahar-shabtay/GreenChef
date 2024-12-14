package com.example.greenchef.Data

import android.content.Context
import androidx.room.Room
import com.example.greenchef.Database.AppDatabase

object DatabaseBuilder {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "greenchef_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}