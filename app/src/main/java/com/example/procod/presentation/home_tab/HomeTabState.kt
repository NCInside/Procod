package com.example.procod.presentation.home_tab

import com.example.procod.model.Challenge
import com.example.procod.model.User

data class HomeTabState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val filterId: Int = 0,
    val challenges: List<Challenge> = emptyList(),
    val users: List<User> = emptyList(),
    val username: String = "",
    val email: String = "",
    val chalComplete: Int = 0,
    val chalMade: Int = 0,
    val chalAttempt: Int = 0
)