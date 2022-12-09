package com.example.procod.presentation.home_tab

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
class HomeTabViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    var state by mutableStateOf(HomeTabState())
    private var searchJob: Job? = null

    init {
        getChallenges()
        getUser(null)
        getStatisticUser()
        getUsers()
    }

    fun onEvent(event: HomeTabEvent) {
        when (event) {
            is HomeTabEvent.Refresh -> {
                getChallenges()
                getUser(null)
                getStatisticUser()
                getUsers()
            }
            is HomeTabEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(750L)
                    getUsers()
                }
            }
        }
    }

    private fun getChallenges() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val challengesResult = repository.getChallenges(null)
            when (val result = challengesResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(
                        challenges = result.data!!
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    private fun getUser(id: Int?) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val userResult = repository.getUser(id)
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

    private fun getStatisticUser() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val statisticResult = repository.getStatisticUser()
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

    private fun getUsers() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val usersResult = repository.getUsers()
            when (val result = usersResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(
                        users = result.data!!.filter { it.Username!!.contains(state.searchQuery, ignoreCase = true) }
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }

}