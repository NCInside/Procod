package com.example.procod.model

data class SubmitRequest(
    val Code: String,
    val Runtime: Int,
    val Is_correct: Boolean,
    val Error: String,
    val Memory: Int,
    val UserID: Int,
    val ChallengeID: Int
)
