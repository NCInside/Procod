package com.example.procod.presentation.challenge_tab.sandbox_tab

import com.example.procod.model.CodeResult

data class SandboxTabState(
    val input: String = "",
    val code: String = "",
    val result: CodeResult? = null,
    val isLoading: Boolean = false,
    val token: String = ""
)
