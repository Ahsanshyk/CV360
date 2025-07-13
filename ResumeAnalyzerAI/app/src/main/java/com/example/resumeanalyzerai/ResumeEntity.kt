package com.example.ResumeAnalyzerAI.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resume_table")
data class ResumeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "job_description") val jobDescription: String,
    @ColumnInfo(name = "resume_path") val resumePath: String,
    @ColumnInfo(name = "similarity_score") val similarityScore: Double,
    @ColumnInfo(name = "recommendations") val recommendations: String
)
