package com.example.procod.presentation.challenge_done

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
class ChallengeDoneViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {
    var state by mutableStateOf(ChallengeDoneState())

    init {
        getChallengesSubmission()
    }

    private fun getChallengesSubmission() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val challengesResult = repository.getChallengesSubmission()
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

}