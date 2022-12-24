package com.example.procod.presentation.profile_tab

import com.example.procod.model.Challenge

data class ProfileTabState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isEditing: Boolean = false,
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val chalComplete: Int = 0,
    val chalMade: Int = 0,
    val chalAttempt: Int = 0,
    val challenges: List<Challenge> = emptyList()
)
