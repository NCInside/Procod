package com.example.procod.presentation.sandbox_tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun SandboxTabScreen(
    id: Int,
    viewModel: SandboxTabViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Column() {
        BasicTextField(
            value = state.code,
            onValueChange = { viewModel.onEvent(SandboxTabEvent.OnCodeChange(it)) },
        )

        BasicTextField(
            value = state.input,
            onValueChange = { viewModel.onEvent(SandboxTabEvent.OnInputChange(it)) },
        )

        if (state.result != null) {
            Text(
                text = "${state.result.stdout} , ${state.result.time} , ${state.result.memory}, ${state.result.status!!.description}"
            )
        }

        Button(onClick = { viewModel.onEvent(SandboxTabEvent.Reset) }) {
            Text(text = "Reset")
        }

        Button(onClick = { viewModel.onEvent(SandboxTabEvent.Run) }) {
            Text(text = "Run")
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