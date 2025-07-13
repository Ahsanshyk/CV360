package com.example.resumeanalyzerai.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResumeDao {

    @Insert
    suspend fun insert(resume: ResumeEntity)

    @Query("SELECT * FROM resume_table WHERE id = :resumeId LIMIT 1")
    suspend fun getResumeById(resumeId: kotlin.Int): ResumeEntity?
}