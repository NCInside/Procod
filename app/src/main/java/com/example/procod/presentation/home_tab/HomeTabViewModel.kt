package com.example.procod.presentation.home_tab

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeTabViewModel @Inject constructor(

): ViewModel() {

    var state by mutableStateOf(HomeTabState())

    init {

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

    private fun getChallenges(
        query: String = state.searchQuery.lowercase()
    ) {
        viewModelScope.launch {

        }
    }

}