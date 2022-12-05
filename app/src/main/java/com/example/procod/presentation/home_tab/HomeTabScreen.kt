package com.example.procod.presentation.home_tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun HomeTabScreen(
    viewModel: HomeTabViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column() {
        Text(
            text = "Home"
        )
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            content = {/* TODO: Challenges State */}
        )
        Text(
            text = "Profile"
        )
        Card() {
            Column() {
                Text(
                    text = "Dummy", //Add UsernameState
                )
                Row() {
                    //Fill in component for User Stat
                }
            }
        }
        Text(
            text = "Leaderboard"
        )
        //TextField Here
        LazyRow(
            content = {/* TODO: Filter Leaderboard by User Stat */}
        )
        LazyColumn(
            content = {/* TODO: Leaderboard */}
        )
    }
}