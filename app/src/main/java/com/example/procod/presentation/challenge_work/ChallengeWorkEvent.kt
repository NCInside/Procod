package com.example.procod.presentation.challenge_work

sealed class ChallengeWorkEvent {
    object Submit: ChallengeWorkEvent()
    object Reset: ChallengeWorkEvent()
    data class OnCodeChange(val query: String): ChallengeWorkEvent()
}
