package com.example.procod.model

data class User(
    val Challenges: List<Challenge>?,
    val CreatedAt: String?,
    val DeletedAt: String?,
    val Email: String?,
    val ID: Int?,
    val Password: String?,
    val Statistics: List<Statistic>?,
    val Submissions: List<Submission>?,
    val UpdatedAt: String?,
    val Username: String?
)