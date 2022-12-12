package com.example.procod.presentation.profile_statistic

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
class ProfileStatisticViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {
    var state by mutableStateOf(ProfileStatisticState())

    init {
        getStatisticsUser()
    }

    fun onEvent(event: ProfileStatisticEvent) {
        when(event) {
            is ProfileStatisticEvent.onFilter -> {
                state = state.copy(filter = event.id)
            }
        }
    }

    private fun getStatisticsUser() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val statisticsResult = repository.getStatisticsUser()
            when (val result = statisticsResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(
                        statistics = result.data!!
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }

}