package com.example.resumeanalyzerai.database


class ResumeRepository(private val resumeDao: ResumeDao) {

    suspend fun insert(resume: ResumeEntity) {
        resumeDao.insert(resume)
    }

    suspend fun getResumeById(resume: Int): ResumeEntity? {
        return resumeDao.getResumeById(resume)
    }
}
