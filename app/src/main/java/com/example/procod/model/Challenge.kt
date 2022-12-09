package com.example.procod.model

data class Challenge(
    val ChallengeExamples: List<Example>?,
    val ChallengeLabels: List<Label>?,
    val ChallengeTargets: List<com.example.procod.model.Target>?,
    val CreatedAt: String?,
    val DeletedAt: String?,
    val Description: String?,
    val ID: Int?,
    val Submissions: List<Submission>?,
    val Title: String?,
    val UpdatedAt: String?,
    val UserID: Int?
)