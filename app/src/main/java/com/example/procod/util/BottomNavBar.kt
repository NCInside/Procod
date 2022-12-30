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
        modifier = Modifier.fillMaxWidth().background(Color.White).padding(vertical = 16.dp),
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
            Icon(Icons.Filled.Home, contentDescription = "Home")
            Text(text = "Home")
        }
        Column(
            modifier = Modifier.clickable {
                navigator.navigate(
                    SandboxTabScreenDestination(0)
                )
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.Star, contentDescription = "Sandbox")
            Text(text = "Sandbox")
        }
        Column(
            modifier = Modifier.clickable {
                navigator.navigate(
                    ChallengeTabScreenDestination(0)
                )
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.List, contentDescription = "Challenge")
            Text(text = "Challenge")
        }
        Column(
            modifier = Modifier.clickable {
                navigator.navigate(
                    ProfileTabScreenDestination(0)
                )
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.Person, contentDescription = "Profile")
            Text(text = "Profile")
        }
    }
}