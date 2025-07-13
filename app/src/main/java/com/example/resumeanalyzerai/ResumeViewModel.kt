package com.example.resumeanalyzerai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resumeanalyzerai.database.ResumeEntity
import com.example.resumeanalyzerai.database.ResumeRepository
import kotlinx.coroutines.launch

class ResumeViewModel(private val repository: ResumeRepository) : ViewModel() {

    // Perform AI analysis on the resume
    fun performResumeAnalysis(resume: ResumeEntity) {
        viewModelScope.launch {
            try {
                // Simulate AI analysis (replace this with actual AI logic)
                val similarityScore = 85 // Example score
                val recommendations = "Improve your experience section."

                // Update resume with analysis results
                val updatedResume = resume.copy(
                    similarityScore = similarityScore,
                    recommendations = recommendations
                )

                // Save updated resume
                repository.insert(updatedResume)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    // Fetch resume by ID
    fun getResumeById(resumeId: Int, callback: (ResumeEntity?) -> Unit) {
        viewModelScope.launch {
            val resume = repository.getResumeById(resumeId)
            callback(resume)
        }
    }
}
