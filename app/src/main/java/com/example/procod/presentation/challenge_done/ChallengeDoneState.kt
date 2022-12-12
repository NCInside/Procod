package com.example.procod.presentation.challenge_done

import com.example.procod.model.Challenge

data class ChallengeDoneState(
    val isLoading: Boolean = false,
    val challenges: List<Challenge> = emptyList()
)
