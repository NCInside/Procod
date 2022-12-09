package com.example.procod.presentation.challenge_tab

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.presentation.challenge_tab.components.ChallengeItem
import com.example.procod.presentation.challenge_tab.components.LabelItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun ChallengeTabScreen(
    id: Int,
    viewModel: ChallengeTabViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    //navigate to challenge make
                }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding())
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = { viewModel.onEvent(ChallengeTabEvent.OnSearchQueryChange(it)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            )
            LazyRow() {
                items(state.labels.size) { i ->
                    val label = state.labels[i]
                    LabelItem(
                        label = label,
                        size = 16.sp,
                        color = if (state.filterId == label.ID) Color.Black else Color.Cyan,
                        modifier = Modifier
                            .clickable {
                                viewModel.onEvent(ChallengeTabEvent.OnFilterIdChange(label.ID!!))
                            }
                    )
                }
            }
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.onEvent(ChallengeTabEvent.Refresh) }
            ) {
                LazyColumn() {
                    items(state.challenges.size) { i ->
                        val challenge = state.challenges[i]
                        ChallengeItem(
                            challenge = challenge,
                            modifier = Modifier
                                .clickable {

                                }
                        )
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(state.isLoading) {
            CircularProgressIndicator()
        }
    }

}