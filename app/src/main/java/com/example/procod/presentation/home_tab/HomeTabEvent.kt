package com.example.procod.presentation.home_tab

sealed class HomeTabEvent {
    object Refresh: HomeTabEvent()
    data class OnSearchQueryChange(val query: String): HomeTabEvent()
}