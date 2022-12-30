package com.example.procod.presentation.profile_tab.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.R
import com.example.procod.model.Challenge
import com.example.procod.model.Label
import com.example.procod.presentation.profile_tab.ProfileTabEvent
import com.example.procod.presentation.profile_tab.ProfileTabViewModel

@Composable
fun ChallengeItem(
    challenge: Challenge,
    modifier: Modifier = Modifier.background(colorResource(R.color.color3)),
    viewModel: ProfileTabViewModel = hiltViewModel()
) {
    Card(shape = RoundedCornerShape(15),
        modifier = modifier
            .fillMaxWidth(),
        backgroundColor = colorResource(R.color.color3)
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(colorResource(R.color.color3)),
        ) {
            Column(

            ) {
                Text(

                    text = challenge.Title!!,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 5.dp)
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = challenge.Description!!,
                        color = Color.White,
                        fontWeight = FontWeight.Light,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        modifier = modifier
                            .width(250.dp)
                            .padding(start = 8.dp)
                    )

                    Button(
                        onClick = { viewModel.onEvent(ProfileTabEvent.Delete(challenge.ID!!)) },
                        modifier = Modifier.width(80.dp).padding(end = 10.dp).size(30.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),

                       ) {
                        Text(text = "Delete", fontSize = 10.sp)
                    }
//                    LazyRow(
//                        modifier = modifier.padding(end = 5.dp)
//                    ) {
//                        items(challenge.ChallengeLabels!!.size) { i ->
//                            val label = challenge.ChallengeLabels[i]
//                            LabelItem(
//                                label = label,
//                                size = 12.sp,
//                                color = Color.Yellow,
//                                modifier = Modifier.padding(horizontal = 3.dp)
//
//                            )
//                        }
//                    }
                }
            }
        }}
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
                ),
                Label(
                    CreatedAt = null,
                    DeletedAt = null,
                    ID = 2,
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