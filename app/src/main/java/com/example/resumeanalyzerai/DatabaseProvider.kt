package com.example.resumeanalyzerai.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    fun getDatabase(context: Context): ResumeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ResumeDatabase::class.java,
            "resume_database"
        ).build()
    }
}
