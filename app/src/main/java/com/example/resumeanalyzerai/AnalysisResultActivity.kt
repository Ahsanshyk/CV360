package com.example.resumeanalyzerai

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.resumeanalyzerai.databinding.ActivityAnalysisResultBinding
import com.example.resumeanalyzerai.database.ResumeDatabase
import com.example.resumeanalyzerai.database.ResumeRepository

class AnalysisResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnalysisResultBinding
    private lateinit var resumeViewModel: ResumeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalysisResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel and other components
        val resumeDao = ResumeDatabase.getDatabase(application).resumeDao()
        val resumeRepository = ResumeRepository(resumeDao)
        val factory = ResumeViewModelFactory(resumeRepository)
        resumeViewModel = ViewModelProvider(this, factory).get(ResumeViewModel::class.java)

        // Set up the back button
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener { finish() }

        // Retrieve and display resume analysis
        val resumeId = intent.getIntExtra("RESUME_ID", -1)
        if (resumeId != -1) {
            resumeViewModel.getResumeById(resumeId) { resume ->
                resume?.let {
                    // If no AI analysis has been performed, trigger it
                    if (resume.similarityScore == 0) {
                        resumeViewModel.performResumeAnalysis(resume)
                    }

                    // Update UI
                    binding.similarityScore.text = "Similarity Score: ${resume.similarityScore}%"
                    binding.recommendations.text = "Recommendations:\n${resume.recommendations}"
                }
            }
        }
    }
}
