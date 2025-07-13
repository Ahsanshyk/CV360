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
                temperature = 0.7f // Control the randomness of the output.
                maxOutputTokens = 500 // Limit the length of the output.
            }
        )
    }

    // Function to analyze the resume against the job description.
    suspend fun analyzeResume(jobDescription: String, resumeText: String): Pair<Int, String> = withContext(Dispatchers.IO) {
        try {
            // Construct the prompt for the AI model to compare resume with job description.
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

            // Call the generative model to get the response.
            val response = generativeModel.generateContent(prompt)

            // Parse the response and extract the similarity score and recommendations.
            parseAnalysisResponse(response.text.toString())
        } catch (e: Exception) {
            // Catch any exceptions and return a failure result.
            Pair(0, "Analysis failed: ${e.localizedMessage}")
        }
    }

    // Function to parse the response from the AI model.
    private fun parseAnalysisResponse(responseText: String): Pair<Int, String> {
        // Regex to extract similarity score and recommendations from the response text.
        val similarityScoreRegex = "Similarity Score: (\\d+)".toRegex()
        val recommendationsRegex = "Recommendations:(.+)".toRegex(RegexOption.DOT_MATCHES_ALL)

        // Find and extract the similarity score, defaulting to 0 if not found.
        val similarityScore = similarityScoreRegex.find(responseText)
            ?.groupValues?.get(1)?.toIntOrNull() ?: 0

        // Find and extract the recommendations, defaulting to "No specific recommendations found." if not found.
        val recommendations = recommendationsRegex.find(responseText)
            ?.groupValues?.get(1)?.trim() ?: "No specific recommendations found."

        // Return the parsed result as a pair of similarity score and recommendations.
        return Pair(similarityScore, recommendations)
    }
}
