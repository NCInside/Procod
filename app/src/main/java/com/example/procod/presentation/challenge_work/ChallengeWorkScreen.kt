package com.example.procod.presentation.challenge_work

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.presentation.challenge_tab.ChallengeTabEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun ChallengeWorkScreen(
    id: Int,
    navigator: DestinationsNavigator,
    viewModel: ChallengeWorkViewModel = hiltViewModel()
) {
    val state = viewModel.state
    if (!state.isLoading) {
        Column() {
            Text(
                text = state.challenge?.Title!!
            )
            Text(
                text = state.challenge.Description!!
            )
            LazyColumn() {
                items(state.challenge.ChallengeExamples!!.size) { i ->
                    val example = state.challenge.ChallengeExamples[i]
                    Row() {
                        Text(text = "Input: ${example.Ex_input!!}")
                        Text(text = "Output: ${example.Ex_output!!}")
                    }
                }
            }
            OutlinedTextField(
                value = state.code,
                onValueChange = { viewModel.onEvent(ChallengeWorkEvent.OnCodeChange(it)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            )
            Row() {
                Button(onClick = {
                    viewModel.onEvent(ChallengeWorkEvent.Submit)
                }) {
                    Text(text = "Submit")
                }
                Button(onClick = { viewModel.onEvent(ChallengeWorkEvent.Reset) }) {
                    Text(text = "Reset")
                }
            }
            if (state.result != null) {
                if (state.result.status?.id!! > 4) {
                    Text(text = "Error; Incorrect Syntax")
                }  else if (state.result.status.id == 3 && state.result.stdout == "${state.challenge.ChallengeTargets?.get(0)!!.Target_output!!}\n") {
                    Text(text = "Correct!")
                } else if (state.result.status.id == 3) {
                    Text(text = "Incorrect!")
                }
            }
        }
    }
}