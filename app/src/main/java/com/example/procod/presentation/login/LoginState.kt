package com.example.procod.presentation.login

data class LoginState(
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val isLogin: Boolean = true,
    val isLoading: Boolean = false,
)