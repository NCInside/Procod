package com.example.procod.presentation.home_tab.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.procod.model.Challenge

@Composable
fun ChallengeCard(
    modifier: Modifier = Modifier,
    challenge: Challenge
) {
    Card() {
        Row() {
            Text(
                text = challenge.ID.toString()
            )
            Text(
                text = challenge.Title.toString()
            )
            Text(
                text = challenge.Description.toString()
            )
        }
    }
}