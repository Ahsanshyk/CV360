package com.example.ResumeAnalyzerAI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ResumeAnalyzerAI.data.ResumeEntity
import com.example.ResumeAnalyzerAI.data.ResumeRepository
import kotlinx.coroutines.launch

class ResumeViewModel(private val resumeRepository: ResumeRepository) : ViewModel() {

    // Function to get a resume by its ID
    fun getResumeById(resumeId: Int, onResult: (ResumeEntity?) -> Unit) {
        viewModelScope.launch {
            val resume = resumeRepository.getResumeById(resumeId)
            onResult(resume)
        }
    }

    // Function to get all resumes
    fun getAllResumes(onResult: (List<ResumeEntity>) -> Unit) {
        viewModelScope.launch {
            val resumes = resumeRepository.getAllResumes()
            onResult(resumes)
        }
    }

    // Function to insert a resume
    fun insertResume(resume: ResumeEntity, onComplete: () -> Unit) {
        viewModelScope.launch {
            resumeRepository.insertResume(resume)
            onComplete()  // Notify when the insert operation is complete
        }
    }

    // Function to delete a resume
    fun deleteResume(resume: ResumeEntity, onComplete: () -> Unit) {
        viewModelScope.launch {
            resumeRepository.deleteResume(resume)
            onComplete()  // Notify when the delete operation is complete
        }
    }
}
