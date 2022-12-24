package com.example.procod.presentation.profile_tab

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.procod.data.repository.AppRepository
import com.example.procod.util.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileTabViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    var state by mutableStateOf(ProfileTabState())

    init {
        getUser(null)
        getStatisticUser()
    }

    fun onEvent(event: ProfileTabEvent) {
        when (event) {
            is ProfileTabEvent.Refresh -> {
                getUser(null)
                getStatisticUser()
            }
            is ProfileTabEvent.OnEmailChange -> {
                state = state.copy(email = event.query)
            }
            is ProfileTabEvent.OnNameChange -> {
                state = state.copy(username = event.query)
            }
            is ProfileTabEvent.OnPassChange -> {
                state = state.copy(password = event.query)
            }
            is ProfileTabEvent.EditProfile -> {
                state = state.copy(isEditing = true)
            }
            is ProfileTabEvent.CancelProfile -> {
                state = state.copy(isEditing = false)
            }
            is ProfileTabEvent.SubmitProfile -> {
                state = state.copy(isEditing = false)
                putUser()
            }
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
                        email = result.data.Email!!,
                        challenges = result.data.Challenges ?: emptyList()
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    private fun putUser() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val userResult = repository.putUser(
                username = state.username,
                email = state.email,
                password = state.password
            )
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

}