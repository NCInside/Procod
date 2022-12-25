package com.example.procod.presentation.home_tab

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.presentation.destinations.ChallengeWorkScreenDestination
import com.example.procod.presentation.home_tab.components.ChallengeCard
import com.example.procod.presentation.home_tab.components.UserCard
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun HomeTabScreen(
    id: Int,
    navigator: DestinationsNavigator,
    viewModel: HomeTabViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)

    Column() {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvent(HomeTabEvent.Refresh) }) {
            Column() {
                Text(
                    text = "Home"
                )
                Text(
                    text = "Challenges"
                )
                LazyRow(
                    modifier = Modifier
                ) {
                    items(state.challenges.size) { i ->
                        val challenge = state.challenges[i]
                        ChallengeCard(
                            challenge = challenge,
                            modifier = Modifier.clickable {
                                navigator.navigate(
                                    ChallengeWorkScreenDestination(challenge.ID!!)
                                )
                            }
                        )
                    }
                }
                Text(
                    text = "Profile"
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = state.username,
                    )
                    Row() {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Challenge Attempted"
                            )
                            Text(
                                text = state.chalAttempt.toString()
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Challenge Completed"
                            )
                            Text(
                                text = state.chalComplete.toString()
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Challenge Made"
                            )
                            Text(
                                text = state.chalMade.toString()
                            )
                        }
                    }
                }
                Text(
                    text = "Leaderboard"
                )
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = { viewModel.onEvent(HomeTabEvent.OnSearchQueryChange(it)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(state.users.size) { i ->
                        UserCard(user = state.users[i])
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