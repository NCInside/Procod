package com.example.procod.presentation.home_tab.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.procod.R
import com.example.procod.model.Challenge
import com.example.procod.presentation.challenge_tab.components.LabelItem
import com.example.procod.presentation.destinations.ChallengeWorkScreenDestination
import com.example.procod.presentation.profile_tab.ProfileTabEvent
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun ChallengeCard(
    modifier: Modifier = Modifier,
    challenge: Challenge,
//    navigator: DestinationsNavigator,
) {
    Card(shape = RoundedCornerShape(10),
        modifier = modifier
            .padding(start = 6.dp)
            .width(110.dp)
            .height(62.dp),
        backgroundColor = colorResource(R.color.color2)
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(colorResource(R.color.color2)),
        ) {
            Column(

            ) {
                Text(

                    text = challenge.Title!!,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
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
                        fontSize = 8.sp,
                        modifier = modifier
                            .width(90.dp)
                            .padding(8.dp)
                    )
//                    Button(
//                        onClick = {  },
//                        modifier = Modifier.width(50.dp).padding(end = 10.dp).size(30.dp),
//                        shape = RoundedCornerShape(50.dp),
//                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
//
//                        ) {
//                        Text(text = "Delete", fontSize = 4.sp)
//                    }
                }
            }
        }
    }
}