package com.example.procod.presentation.sandbox_tab

sealed class SandboxTabEvent {
    object Run: SandboxTabEvent()
    object Reset: SandboxTabEvent()
    data class OnCodeChange(val query: String): SandboxTabEvent()
    data class OnInputChange(val query: String): SandboxTabEvent()
}
