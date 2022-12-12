package com.example.procod.presentation.challenge_make

data class ChallengeMakeState(
    val isLoading: Boolean = false,
    val exampleCount: Int = 1,
    val targetCount: Int = 1,
    val labelsId: MutableList<Int> = mutableListOf()
)
