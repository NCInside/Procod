package com.example.procod.presentation.profile_tab

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.presentation.destinations.ChallengeMakeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun ProfileTabScreen(
    id: Int,
    navigator: DestinationsNavigator,
    viewModel: ProfileTabViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val activity = LocalContext.current as? Activity

    if (!state.isLoading) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.onEvent(ProfileTabEvent.EditProfile)
                    }
            ) {
                Text(
                    text = state.username,
                )
                Text(
                    text = state.email,
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
            Text(text = "Challenges Made")
            LazyColumn() {
                items(state.challenges.size) { i ->
                    val challenge = state.challenges[i]
                    Row() {
                        Column(
                            modifier = Modifier.clickable {
                                navigator.navigate(
                                    ChallengeMakeScreenDestination(challenge.ID!!)
                                )
                            }
                        ){
                            Text(text = challenge.Title!!)
                            Text(text = challenge.Description!!)
                        }
                        Button(onClick = { viewModel.onEvent(ProfileTabEvent.Delete(challenge.ID!!)) }) {
                            Text(text = "Delete")
                        }
                    }
                }
            }
            Row() {
                Button(onClick = {
                    viewModel.onEvent(ProfileTabEvent.DeleteProfile)
                    activity?.finish()
                }) {
                    Text(text = "Delete Profile")
                }
                Button(onClick = {
                    viewModel.onEvent(ProfileTabEvent.Logout)
                    activity?.finish()
                }) {
                    Text(text = "Logout")
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

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(state.isEditing) {
            Column() {
                OutlinedTextField(
                    value = state.username,
                    onValueChange = { viewModel.onEvent(ProfileTabEvent.OnNameChange(it)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                )
                OutlinedTextField(
                    value = state.email,
                    onValueChange = { viewModel.onEvent(ProfileTabEvent.OnEmailChange(it)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                )
                OutlinedTextField(
                    value = state.password,
                    onValueChange = { viewModel.onEvent(ProfileTabEvent.OnPassChange(it)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                )
                Row() {
                    Button(onClick = { viewModel.onEvent(ProfileTabEvent.CancelProfile) }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = { viewModel.onEvent(ProfileTabEvent.SubmitProfile) }) {
                        Text(text = "Submit")
                    }
                }
            }
        }
    }

}