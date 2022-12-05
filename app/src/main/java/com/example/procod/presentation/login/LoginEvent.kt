package com.example.procod.presentation.login

sealed class LoginEvent {
    data class OnUsernameChange(val content: String): LoginEvent()
    data class OnPasswordChange(val content: String): LoginEvent()
    data class OnEmailChange(val content: String): LoginEvent()
    object Switch: LoginEvent()
    object ButtonClick: LoginEvent()
}