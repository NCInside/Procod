package com.example.procod.presentation.home_tab.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.procod.model.Challenge

@Composable
fun ChallengeCard(
    modifier: Modifier = Modifier,
    challenge: Challenge
) {
    Card(
        modifier = modifier.size(100.dp)
    ) {
        Column() {
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