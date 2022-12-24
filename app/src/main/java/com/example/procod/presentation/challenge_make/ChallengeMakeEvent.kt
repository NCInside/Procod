package com.example.procod.presentation.challenge_make

sealed class ChallengeMakeEvent {
    object Refresh: ChallengeMakeEvent()
    object SubmitExample: ChallengeMakeEvent()
    object SubmitTarget: ChallengeMakeEvent()
    object Submit: ChallengeMakeEvent()
    data class OnTitleChange(val query: String): ChallengeMakeEvent()
    data class OnDescriptionChange(val query: String): ChallengeMakeEvent()
    data class OnExInputChange(val query: String): ChallengeMakeEvent()
    data class OnExOutputChange(val query: String): ChallengeMakeEvent()
    data class OnExDescriptionChange(val query: String): ChallengeMakeEvent()
    data class OnInputChange(val query: String): ChallengeMakeEvent()
    data class OnOutputChange(val query: String): ChallengeMakeEvent()
    data class AddLabel(val id: Int): ChallengeMakeEvent()
    data class MinLabel(val id: Int): ChallengeMakeEvent()
    data class DeleteExample(val id: Int): ChallengeMakeEvent()
    data class DeleteTarget(val id: Int): ChallengeMakeEvent()
}
