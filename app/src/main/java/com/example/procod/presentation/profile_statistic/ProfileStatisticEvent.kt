package com.example.procod.presentation.profile_statistic

sealed class ProfileStatisticEvent {
    data class onFilter(val id: Int): ProfileStatisticEvent()
}
