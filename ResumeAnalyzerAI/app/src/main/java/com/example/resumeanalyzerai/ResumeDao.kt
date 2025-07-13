package com.example.ResumeAnalyzerAI.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResumeDao {
    @Insert
    suspend fun insertResume(resume: ResumeEntity): Long

    @Delete
    suspend fun deleteResume(resume: ResumeEntity): Int

    @Query("SELECT * FROM resume_table WHERE id = :resumeId LIMIT 1")
    suspend fun getResumeById(resumeId: Int): ResumeEntity?

    @Query("SELECT * FROM resume_table")
    suspend fun getAllResumes(): List<ResumeEntity>
}



