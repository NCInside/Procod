package com.example.procod.presentation.login

import com.example.procod.model.User
import com.example.procod.model.UserData

data class LoginState(
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val isLogin: Boolean = true,
    val isLoading: Boolean = false,
)