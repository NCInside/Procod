package com.example.procod.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.procod.data.repository.AppRepository
import com.example.procod.model.User
import com.example.procod.model.UserData
import com.example.procod.util.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    var state by mutableStateOf(LoginState())

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResults = resultChannel.receiveAsFlow()

    init {
        authenticate()
    }

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.OnUsernameChange -> {
                state = state.copy(username = event.content)
            }
            is LoginEvent.OnPasswordChange -> {
                state = state.copy(password = event.content)
            }
            is LoginEvent.OnEmailChange -> {
                state = state.copy(email = event.content)
            }
            is LoginEvent.Switch -> {
                state = state.copy(isLogin = !state.isLogin)
            }
            is LoginEvent.ButtonClick -> {
                if (state.isLogin) login() else register()
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.register(
                username = state.username,
                email = state.email,
                password = state.password
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.login(
                email = state.email,
                password = state.password
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun authenticate() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.authenticate()
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

}