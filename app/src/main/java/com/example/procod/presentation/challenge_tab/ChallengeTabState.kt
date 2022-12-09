package com.example.procod.presentation.challenge_tab

import com.example.procod.model.Challenge
import com.example.procod.model.Label

data class ChallengeTabState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val filterId: Int? = null,
    val challenges: List<Challenge> = emptyList(),
    val labels: List<Label> = emptyList(),
)