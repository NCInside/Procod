package com.example.procod.presentation.home_tab.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.procod.model.User

@Composable
fun UserCard(
    user: User,
    modifier: Modifier = Modifier
) {
    Card() {
        Row() {
            Text(
                text = user.Username!!
            )
            Text(
                text = user.Statistics?.get(0)?.Num_challenge_completed.toString()
            )
        }
    }
}