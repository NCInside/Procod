package com.example.procod.presentation.challenge_made

import com.example.procod.model.Challenge
import com.example.procod.model.Label
import com.example.procod.model.Statistic

data class ChallengeMadeState(
    val isLoading: Boolean = false,
    val labels: List<Label> = emptyList(),
    val challenge: Challenge? = null,
    val title: String = "",
    val description: String = "",
    val ex_input: String = "",
    val ex_output: String = "",
    val ex_description: String = "",
    val input: String = "",
    val output: String = "",
    val labelId: Int = -1,
    val statistic: Statistic? = null
)
