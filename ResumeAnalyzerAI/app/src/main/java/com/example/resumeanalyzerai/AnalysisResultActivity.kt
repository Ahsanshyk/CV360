package com.example.ResumeAnalyzerAI

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ResumeAnalyzerAI.data.ResumeDatabase
import com.example.ResumeAnalyzerAI.data.ResumeRepository
import com.example.ResumeAnalyzerAI.data.ResumeViewModelFactory
import com.example.ResumeAnalyzerAI.databinding.ActivityAnalysisResultBinding

class AnalysisResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnalysisResultBinding
    private lateinit var resumeViewModel: ResumeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalysisResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ResumeRepository
        val resumeDao = ResumeDatabase.getDatabase(application).resumeDao()  // Ensure this is correctly initialized
        val resumeRepository = ResumeRepository(resumeDao)

        // Create ViewModel using ResumeViewModelFactory
        val factory = ResumeViewModelFactory(resumeRepository)
        resumeViewModel = ViewModelProvider(this, factory).get(ResumeViewModel::class.java)

        // Now you can use your ViewModel methods
        val resumeId = 1  // Example resumeId (this can be dynamic)
        resumeViewModel.getResumeById(resumeId) { resume ->
            if (resume != null) {
                // Update your UI with the data
                binding.similarityScore.text = "Similarity Score: ${resume.similarityScore}"
                binding.recommendations.text = "Recommendations: ${resume.recommendations}"
            } else {
                // Handle resume not found
                Toast.makeText(this, "Resume not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
