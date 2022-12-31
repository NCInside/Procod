package com.example.procod.presentation.home_tab.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.DefaultFocusProperties.start
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.procod.R
import com.example.procod.model.User
import com.example.procod.presentation.destinations.ProfileTabScreenDestination
import com.example.procod.presentation.home_tab.HomeTabState
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun UserCard(
    user: User,
    modifier: Modifier = Modifier,
    rank: Int,
    state: HomeTabState

) {

    Card(modifier = Modifier.fillMaxWidth()
//        .background(if (user.ID == 1) Color.Yellow else Color.White)
        .padding(start= 0.dp).padding(vertical =5.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth()
                .background(if (user.Username == state.username) Color.Yellow else Color.White)
                .padding(start= 0.dp).padding(top =3.dp)
                , horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(
                text = rank.toString(),
//                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Row(modifier = Modifier
                .width(220.dp)
                .padding(horizontal = 60.dp)
                , horizontalArrangement = Arrangement.Start

            ){

                Image(painter = painterResource(id = R.drawable.ic_baseline_account_circle_24,),
                contentDescription ="Picture" ,
                modifier = Modifier
                    .size(30.dp)
                    .padding(end =10.dp).padding(bottom = 6.dp),
            )
                Text(
                    text = user.Username!!,
                    modifier = Modifier
//                        .padding(top=4.dp)
                        .padding(end = 10.dp)
                    , maxLines = 1
                    ,textAlign = TextAlign.Center
                )}

            if (user.Statistics?.size!!  > 0){
            Text(
                text = user.Statistics?.get(0)?.Num_challenge_completed.toString(),
//                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )}
            else {
                Text(
                    text = "0",
//                modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}