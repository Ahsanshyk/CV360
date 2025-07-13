package com.example.ResumeAnalyzerAI.data

class ResumeRepository(private val resumeDao: ResumeDao) {

    // Function to get a resume by ID
    suspend fun getResumeById(resumeId: Int): ResumeEntity? {
        return resumeDao.getResumeById(resumeId)
    }

    // Function to get all resumes
    suspend fun getAllResumes(): List<ResumeEntity> {
        return resumeDao.getAllResumes()
    }

    // Function to insert a resume
    suspend fun insertResume(resume: ResumeEntity) {
        resumeDao.insertResume(resume)
    }

    // Function to delete a resume
    suspend fun deleteResume(resume: ResumeEntity) {
        resumeDao.deleteResume(resume)
    }
}
