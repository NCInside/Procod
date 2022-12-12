package com.example.procod.presentation.profile_statistic

import com.example.procod.model.Statistic

data class ProfileStatisticState(
    val isLoading: Boolean = false,
    val statistics: List<Statistic> = emptyList(),
    val filter: Int = 0
)
