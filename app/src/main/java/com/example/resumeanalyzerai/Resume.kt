package com.example.resumeanalyzerai

data class Resume(
    val jobDescription: String,
    val resumeText: String,
    val similarityScore: Float? = null,
    val recommendations: String? = null
)
