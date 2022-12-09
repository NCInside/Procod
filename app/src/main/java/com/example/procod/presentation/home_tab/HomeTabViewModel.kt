package com.example.procod.presentation.home_tab

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.procod.data.repository.AppRepository
import com.example.procod.model.Challenge
import com.example.procod.util.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeTabViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    var state by mutableStateOf(HomeTabState())

    init {
        getChallenges()
        getUser()
        getStatistic()
    }

    fun onEvent(event: HomeTabEvent) {
        when(event) {
            is HomeTabEvent.Refresh -> {

            }
            is HomeTabEvent.OnSearchQueryChange -> {

            }
            is HomeTabEvent.OnFilterIdChange -> {

            }
        }
    }

    private fun getChallenges() {
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

    private fun getUser() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val userResult = repository.getUser()
            when (val result = userResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(
                        username = result.data?.Username!!,
                        email = result.data.Email!!
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    private fun getStatistic() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val statisticResult = repository.getStatistic()
            when (val result = statisticResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(
                        chalMade = result.data?.Num_challenge_made!!,
                        chalComplete = result.data.Num_challenge_completed!!,
                        chalAttempt = result.data.Num_challenge_attempted!!
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }

}