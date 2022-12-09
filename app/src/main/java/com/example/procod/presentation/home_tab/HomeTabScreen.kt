package com.example.procod.presentation.home_tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.presentation.home_tab.components.ChallengeCard
import com.example.procod.presentation.home_tab.components.UserCard
import com.example.procod.presentation.login.LoginEvent
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun HomeTabScreen(
    id: Int,
    viewModel: HomeTabViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column() {
        Text(
            text = "Home"
        )
        Text(
            text = "Challenges"
        )
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier
        ) {
            items(state.challenges.size) { i ->
                ChallengeCard(challenge = state.challenges[i])
            }
        }
        Text(
            text = "Profile"
        )
        Card() {
            Column() {
                Text(
                    text = state.username,
                )
                Row() {
                    Column() {
                        Text(
                            text = "Challenge Attempted"
                        )
                        Text(
                            text = state.chalAttempt.toString()
                        )
                    }
                    Column() {
                        Text(
                            text = "Challenge Completed"
                        )
                        Text(
                            text = state.chalComplete.toString()
                        )
                    }
                    Column() {
                        Text(
                            text = "Challenge Made"
                        )
                        Text(
                            text = state.chalMade.toString()
                        )
                    }
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
        Row(

        ) {
            TextButton(
                onClick = {

                }
            ) {
                Text(text = "Challenge Completed")
            }
            TextButton(
                onClick = {

                }
            ) {
                Text(text = "Challenge Made")
            }
            TextButton(
                onClick = {

                }
            ) {
                Text(text = "Challenge Attempted")
            }
        }
        LazyColumn(

        ) {
            items(state.users.size) { i ->
                UserCard(user = state.users[i])
            }
        }
    }
}