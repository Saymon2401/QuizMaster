package com.example.quizmaster

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
