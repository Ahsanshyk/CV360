package com.example.resumeanalyzerai

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.resumeanalyzerai.database.ResumeDatabase
import com.example.resumeanalyzerai.database.ResumeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobDescriptionActivity : AppCompatActivity() {

    private val PDF_REQUEST_CODE = 1
    private var resumePath: String? = null
    private lateinit var database: ResumeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_description)

        database = Room.databaseBuilder(
            applicationContext,
            ResumeDatabase::class.java, "resume_database"
        ).build()

        val jobDescriptionInput: EditText = findViewById(R.id.job_description_input)
        val uploadButton: Button = findViewById(R.id.upload_resume_button)
        val submitButton: Button = findViewById(R.id.submit_button)

        uploadButton.setOnClickListener {
            openFileChooser()
        }

        submitButton.setOnClickListener {
            val jobDescription = jobDescriptionInput.text.toString()
            if (jobDescription.isNotBlank() && resumePath != null) {
                saveToDatabase(jobDescription, resumePath!!)
            } else {
                Toast.makeText(this, "Please enter job description and upload a resume!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openFileChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf"
        this.startActivityForResult(intent, PDF_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PDF_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val fileUri: Uri? = data?.data
            if (fileUri != null) {
                resumePath = fileUri.toString()
                Toast.makeText(this, "Resume selected!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveToDatabase(jobDescription: String, resumePath: String) {
        val resume = ResumeEntity(jobDescription = jobDescription, resumeText = resumePath)

//       CoroutineScope(Dispatchers.IO).launch {
//            database.resumeDao().Resume(resume)
//            runOnUiThread {
        Toast.makeText(this@JobDescriptionActivity, "Data saved successfully!", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@JobDescriptionActivity, AnalysisResultActivity::class.java)
            startActivity(intent)
        }, 3000)

//            }
//        }
    }
}
