package com.example.procod.presentation.challenge_make

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.procod.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChallengeMakeViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {
    var state by mutableStateOf(ChallengeMakeState())

    fun onEvent(event: ChallengeMakeEvent) {
        when(event) {

        }
    }

}