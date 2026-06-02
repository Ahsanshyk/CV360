package com.example.resumeanalyzerai

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiAnalysisRepository {


    private val generativeModel by lazy {
        GenerativeModel(
            modelName = "gemini-pro",
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = generationConfig {
                temperature = 0.7f
                maxOutputTokens = 500
            }
        )
    }


    suspend fun analyzeResume(jobDescription: String, resumeText: String): Pair<Int, String> = withContext(Dispatchers.IO) {
        try {
            val prompt = """
            Carefully compare the resume with the job description and provide:
            1. A similarity score between 0-100
            2. Detailed recommendations for improvement

            Job Description:
            $jobDescription

            Resume:
            $resumeText

            Response Format:
            Similarity Score: [0-100]
            Recommendations:
            1. [First recommendation]
            2. [Second recommendation]
            3. [Third recommendation]
            """.trimIndent()
            val response = generativeModel.generateContent(prompt)

            parseAnalysisResponse(response.text.toString())
        } catch (e: Exception) {

            Pair(0, "Analysis failed: ${e.localizedMessage}")
        }
    }
    private fun parseAnalysisResponse(responseText: String): Pair<Int, String> {
        val similarityScoreRegex = "Similarity Score: (\\d+)".toRegex()
        val recommendationsRegex = "Recommendations:(.+)".toRegex(RegexOption.DOT_MATCHES_ALL)

        val similarityScore = similarityScoreRegex.find(responseText)
            ?.groupValues?.get(1)?.toIntOrNull() ?: 0
        val recommendations = recommendationsRegex.find(responseText)
            ?.groupValues?.get(1)?.trim() ?: "No specific recommendations found."

        return Pair(similarityScore, recommendations)
    }
}
