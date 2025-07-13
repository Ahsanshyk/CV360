package com.example.resumeanalyzerai.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resume_table")
data class ResumeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val jobDescription: String,
    val resumeText: String,
    val similarityScore: Int = 0,  // Default to 0
    val recommendations: String = "" // Default to empty string
)
