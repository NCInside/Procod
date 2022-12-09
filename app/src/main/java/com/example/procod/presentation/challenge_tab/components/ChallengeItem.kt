package com.example.procod.presentation.challenge_tab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.procod.model.Challenge
import com.example.procod.model.Label

@Composable
fun ChallengeItem(
    challenge: Challenge,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        Column(

        ) {
            Text(
                text = challenge.Title!!,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = challenge.Description!!,
                    fontWeight = FontWeight.Light,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    modifier = modifier.weight(6f)
                )
                LazyRow(
                    modifier = modifier.weight(4f)
                ) {
                    items(challenge.ChallengeLabels!!.size) { i ->
                        val label = challenge.ChallengeLabels[i]
                        LabelItem(
                            label = label,
                            size = 12.sp,
                            color = Color.Blue
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ChallengeItemPrev() {
    ChallengeItem(
        challenge = Challenge(
            ChallengeExamples = null,
            ChallengeLabels = listOf(
                Label(
                    CreatedAt = null,
                    DeletedAt = null,
                    ID = 1,
                    Label = "Bruh",
                    UpdatedAt = null
                )
            ),
            ChallengeTargets = null,
            CreatedAt = null,
            DeletedAt = null,
            ID = 1,
            Description = "lorem ipsum summa luma dama bla bla some bullshi",
            Submissions = null,
            Title = "Challenge 1",
            UpdatedAt = null,
            UserID = null
        )
    )
}