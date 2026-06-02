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
        val resumeDao = ResumeDatabase.getDatabase(application).resumeDao()
        val resumeRepository = ResumeRepository(resumeDao)
        val factory = ResumeViewModelFactory(resumeRepository)
        this.resumeViewModel = ViewModelProvider(this, factory).get(ResumeViewModel::class.java)

        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener { finish() }


        val resumeId = intent.getIntExtra("RESUME_ID", -1)
        if (resumeId != -1) {
            resumeViewModel.getResumeById(resumeId) { resume ->
                resume?.let {

                    if (resume.similarityScore == 0) {
                        resumeViewModel.performResumeAnalysis(resume)
                    }
                    binding.similarityScore.text = "Similarity Score: ${resume.similarityScore}%"
                    binding.recommendations.text = "Recommendations:\n${resume.recommendations}"
                }
            }
        }
    }
}
