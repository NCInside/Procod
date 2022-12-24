package com.example.procod.presentation.challenge_make

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun ChallengeMakeScreen(
    id: Int,
    viewModel: ChallengeMakeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    
    if (!state.isLoading) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = state.title,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnTitleChange(it)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            )
            OutlinedTextField(
                value = state.description,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnDescriptionChange(it)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            )
            if (state.challenge?.ChallengeExamples != null) {
                LazyColumn() {
                    items(state.challenge.ChallengeExamples.size!!) { i ->
                        val example = state.challenge.ChallengeExamples[i]
                        Row() {
                            Text(text = "Input: ${example.Ex_input!!}")
                            Text(text = "Output: ${example.Ex_output!!}")
                            Text(text = "Description: ${example.Description!!}")
                            Button(onClick = { viewModel.onEvent(ChallengeMakeEvent.DeleteExample(example.ID!!)) }) {
                                Text(text = "Delete Example")
                            }
                        }
                    }
                }
            }
            OutlinedTextField(
                value = state.ex_input,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnExInputChange(it)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            )
            OutlinedTextField(
                value = state.ex_output,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnExOutputChange(it)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            )
            OutlinedTextField(
                value = state.ex_description,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnExDescriptionChange(it)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            )
            Button(onClick = { viewModel.onEvent(ChallengeMakeEvent.SubmitExample) }) {
                Text(text = "Submit Example")
            }
            if (state.challenge?.ChallengeTargets != null) {
                LazyColumn() {
                    items(state.challenge.ChallengeTargets.size!!) { i ->
                        val target = state.challenge.ChallengeTargets[i]
                        Row() {
                            Text(text = "Input: ${target.Input!!}")
                            Text(text = "Output: ${target.Target_output!!}")
                            Button(onClick = { viewModel.onEvent(ChallengeMakeEvent.DeleteTarget(target.ID!!)) }) {
                                Text(text = "Delete Target")
                            }
                        }
                    }
                }
            }
            OutlinedTextField(
                value = state.input,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnInputChange(it)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            )
            OutlinedTextField(
                value = state.output,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnOutputChange(it)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            )
            Button(onClick = { viewModel.onEvent(ChallengeMakeEvent.SubmitTarget) }) {
                Text(text = "Submit Target")
            }
            if (state.challenge?.ChallengeLabels != null) {
                LazyRow() {
                    items(state.challenge.ChallengeLabels.size!!) { i ->
                        val label = state.challenge.ChallengeLabels[i]
                        Text(
                            text = label.Label!!,
                            modifier = Modifier
                                .clickable { viewModel.onEvent(ChallengeMakeEvent.MinLabel(label.ID!!)) }
                        )
                    }
                }
            }
            LazyRow() {
                items(state.labels.size) { i ->
                    val label = state.labels[i]
                    if (state.challenge?.ChallengeLabels == null) {
                        Text(
                            text = label.Label!!,
                            modifier = Modifier
                                .clickable { viewModel.onEvent(ChallengeMakeEvent.AddLabel(label.ID!!)) }
                        )
                    } else if (!state.challenge.ChallengeLabels.contains(label)) {
                        Text(
                            text = label.Label!!,
                            modifier = Modifier
                                .clickable { viewModel.onEvent(ChallengeMakeEvent.AddLabel(label.ID!!)) }
                        )
                    }
                }
            }
            Button(onClick = { viewModel.onEvent(ChallengeMakeEvent.Submit) }) {
                Text(text = "Submit")
            }
        }
    }
}