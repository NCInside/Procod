package com.example.procod.presentation.challenge_tab

sealed class ChallengeTabEvent {
    object Refresh: ChallengeTabEvent()
    data class OnSearchQueryChange(val query: String): ChallengeTabEvent()
    data class OnFilterIdChange(val id: Int): ChallengeTabEvent()
}
