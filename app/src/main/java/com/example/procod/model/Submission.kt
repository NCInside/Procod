package com.example.procod.model

data class Submission(
    val CreatedAt: String?,
    val DeletedAt: String?,
    val ID: Int?,
    val Code: String?,
    val Runtime: Int?,
    val Is_correct: Boolean?,
    val Error: String?,
    val Memory: Int?,
    val UserID: Int?,
    val UpdatedAt: String?,
    val ChallengeID: Int?
)
