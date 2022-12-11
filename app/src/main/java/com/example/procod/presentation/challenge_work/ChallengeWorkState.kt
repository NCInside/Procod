package com.example.procod.presentation.challenge_work

import com.example.procod.model.Challenge
import com.example.procod.model.CodeResult
import com.example.procod.model.Statistic

data class ChallengeWorkState(
    val isLoading: Boolean = false,
    val challenge: Challenge? = null,
    val code: String = "",
    val token: String = "",
    val result: CodeResult? = null,
    val statistic: Statistic? = null
)
