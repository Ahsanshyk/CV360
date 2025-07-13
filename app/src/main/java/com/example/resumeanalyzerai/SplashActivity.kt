package com.example.resumeanalyzerai

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.resumeanalyzerai.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Navigate to Job Description Input Screen after 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, JobDescriptionActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000ms = 3 seconds
    }
}
