// File: ResumeViewModelFactory.kt
package com.example.ResumeAnalyzerAI.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ResumeAnalyzerAI.ResumeViewModel
import com.example.ResumeAnalyzerAI.data.ResumeViewModelFactory


class ResumeViewModelFactory(private val resumeRepository: ResumeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResumeViewModel::class.java)) {
            return ResumeViewModel(resumeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

