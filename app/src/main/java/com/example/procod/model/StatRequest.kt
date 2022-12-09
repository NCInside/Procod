package com.example.procod.model

data class StatRequest(
    val Num_challenge_attempted: Int,
    val Num_challenge_completed: Int,
    val Num_challenge_made: Int,
    val Total_memory: Int,
    val Total_runtime: Int,
    val UserID: Int
)
