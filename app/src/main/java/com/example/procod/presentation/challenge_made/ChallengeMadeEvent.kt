package com.example.procod.presentation.challenge_made

sealed class ChallengeMadeEvent {
    object SubmitExample: ChallengeMadeEvent()
    object SubmitTarget: ChallengeMadeEvent()
    object Submit: ChallengeMadeEvent()
    data class OnTitleChange(val query: String): ChallengeMadeEvent()
    data class OnDescriptionChange(val query: String): ChallengeMadeEvent()
    data class OnExInputChange(val query: String): ChallengeMadeEvent()
    data class OnExOutputChange(val query: String): ChallengeMadeEvent()
    data class OnExDescriptionChange(val query: String): ChallengeMadeEvent()
    data class OnInputChange(val query: String): ChallengeMadeEvent()
    data class OnOutputChange(val query: String): ChallengeMadeEvent()
    data class AddLabel(val id: Int): ChallengeMadeEvent()
    data class MinLabel(val id: Int): ChallengeMadeEvent()
    data class DeleteExample(val id: Int): ChallengeMadeEvent()
    data class DeleteTarget(val id: Int): ChallengeMadeEvent()
}
