package com.example.procod.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.style.TextDrawStyle.Unspecified.color
import androidx.compose.ui.unit.dp
import com.example.procod.presentation.destinations.ChallengeTabScreenDestination
import com.example.procod.presentation.destinations.HomeTabScreenDestination
import com.example.procod.presentation.destinations.ProfileTabScreenDestination
import com.example.procod.presentation.destinations.SandboxTabScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun BottomNavBar(
    navigator: DestinationsNavigator
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(com.example.procod.R.color.color1))
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.clickable {
                navigator.navigate(
                    HomeTabScreenDestination(0)
                )
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.Home, contentDescription = "Home", tint= Color.White)
            Text(text = "Home", color = Color.White)
        }
        Column(
            modifier = Modifier.clickable {
                navigator.navigate(
                    SandboxTabScreenDestination(0)
                )
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.Star, contentDescription = "Sandbox", tint= Color.White)
            Text(text = "Sandbox", color = Color.White)
        }
        Column(
            modifier = Modifier.clickable {
                navigator.navigate(
                    ChallengeTabScreenDestination(0)
                )
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.List, contentDescription = "Challenge",tint= Color.White)
            Text(text = "Challenge", color = Color.White)
        }
        Column(
            modifier = Modifier.clickable {
                navigator.navigate(
                    ProfileTabScreenDestination(0)
                )
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.Person, contentDescription = "Profile",tint= Color.White)
            Text(text = "Profile", color = Color.White)
        }
    }
}