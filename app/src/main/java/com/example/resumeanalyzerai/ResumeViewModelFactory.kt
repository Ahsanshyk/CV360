package com.example.resumeanalyzerai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.resumeanalyzerai.database.ResumeRepository

class ResumeViewModelFactory(private val resumeRepository: ResumeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResumeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResumeViewModel(resumeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}