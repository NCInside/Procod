package com.example.procod.model

data class CodeResult(
    val compile_output: String?,
    val memory: Int?,
    val message: String?,
    val status: Status?,
    val stderr: String?,
    val stdout: String?,
    val time: String?,
    val token: String?
)