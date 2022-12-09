package com.example.procod.presentation.challenge_tab

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.procod.data.repository.AppRepository
import com.example.procod.util.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeTabViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    var state by mutableStateOf(ChallengeTabState())
    private var searchJob: Job? = null

    init {
        getLabels()
        getChallenges(null)
    }

    fun onEvent(event: ChallengeTabEvent) {
        when(event) {
            is ChallengeTabEvent.Refresh -> {
                getChallenges(state.filterId)
            }
            is ChallengeTabEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(750L)
                    getChallenges(state.filterId)
                }
            }
            is ChallengeTabEvent.OnFilterIdChange -> {
                state = if (event.id == state.filterId) state.copy(filterId = null) else state.copy(filterId = event.id)
                getLabels()
                getChallenges(state.filterId)
            }
        }
    }

    private fun getChallenges(label: Int?) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val challengeResult = repository.getChallenges(null)
            when (val result = challengeResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(
                        challenges = result.data!!
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    private fun getLabels() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val labelResult = repository.getLabels()
            when (val result = labelResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(
                        labels = result.data!!
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }

}