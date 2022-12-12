package com.example.procod.presentation.profile_tab

data class ProfileTabState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val username: String = "",
    val email: String = "",
    val chalComplete: Int = 0,
    val chalMade: Int = 0,
    val chalAttempt: Int = 0
)
