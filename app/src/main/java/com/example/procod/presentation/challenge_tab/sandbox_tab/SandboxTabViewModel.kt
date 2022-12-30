package com.example.procod.presentation.challenge_tab.sandbox_tab

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.procod.data.repository.JudgeRepository
import com.example.procod.util.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SandboxTabViewModel @Inject constructor(
    private val repository: JudgeRepository
): ViewModel() {

    var state by mutableStateOf(SandboxTabState())

    fun onEvent(event: SandboxTabEvent) {
        when(event) {
            is SandboxTabEvent.Reset -> {
                state = state.copy(
                    input = "",
                    result = null,
                    code = "",
                    isLoading = false,
                    token = ""
                )
            }
            is SandboxTabEvent.Run -> {
                runCode(state.code, state.input)
//                getResult(state.token)
            }
            is SandboxTabEvent.OnCodeChange -> {
                state = state.copy(code = event.query)
            }
            is SandboxTabEvent.OnInputChange -> {
                state = state.copy(input = event.query)
            }
        }
    }

    private fun runCode(code: String, input: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val tokenResult = repository.submitCode(code = code, stdin = input)
            when (val result = tokenResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(token = result.data!!.token)
                    coroutineScope {
                        while (state.result == null || state.result?.status?.id!! < 3) {
                            getResult(state.token)
                            delay(100)
                        }
                    }
                }
                is AuthResult.Unauthorized -> {
                    state = state.copy(token = "error1")
                }
                is AuthResult.UnknownError -> {
                    state = state.copy(token = "error2")
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    private fun getResult(token: String) {
        viewModelScope.launch {
            val codeResult = repository.getResult(token)
            when (val result = codeResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(result = result.data)
                }
            }
        }
    }

}