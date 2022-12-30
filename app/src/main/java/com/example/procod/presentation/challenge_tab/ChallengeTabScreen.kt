package com.example.procod.presentation.challenge_tab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.R
import com.example.procod.presentation.challenge_tab.components.ChallengeItem
import com.example.procod.presentation.challenge_tab.components.LabelItem
import com.example.procod.presentation.challenge_tab.components.LabelItem2
import com.example.procod.presentation.destinations.ChallengeMakeScreenDestination
import com.example.procod.presentation.destinations.ChallengeWorkScreenDestination
import com.example.procod.presentation.destinations.SandboxTabScreenDestination
import com.example.procod.util.BottomNavBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun ChallengeTabScreen(
    id: Int,
    navigator: DestinationsNavigator,
    viewModel: ChallengeTabViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier,
                onClick = {
                    navigator.navigate(
                        ChallengeMakeScreenDestination(-1)
                    )
                },
                backgroundColor = colorResource(R.color.color1),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        bottomBar = {
            BottomNavBar(navigator = navigator)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding())
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Challenges",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                modifier = Modifier
                    .padding(bottom = 10.dp))
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    placeholderColor = Color.Black,
                    disabledPlaceholderColor= Color.Black,
                    disabledLabelColor = Color.Black,
                    errorLabelColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black,
                    errorCursorColor = Color.Black,

                ),
                label = { Text(text = "Search")},
                value = state.searchQuery,
                onValueChange = { viewModel.onEvent(ChallengeTabEvent.OnSearchQueryChange(it)) },
                modifier = Modifier
                    .padding(bottom = 4.dp)
//                    .background(color = Color.LightGray)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(0.dp)
            ) {
                items(state.labels.size) { i ->
                    val label = state.labels[i]
                    LabelItem2(
                        label = label,
                        size = 16.sp,
                        color = if (state.filterId == label.ID) Color.Black else colorResource(R.color.color1),
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .padding(vertical = 6.dp)
                            .clickable {
                                viewModel.onEvent(ChallengeTabEvent.OnFilterIdChange(label.ID!!))
                            }
                    )
                }
            }
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.onEvent(ChallengeTabEvent.Refresh) }
            ) {
                LazyColumn() {
                    items(state.challenges.size) { i ->
                        val challenge = state.challenges[i]
                        ChallengeItem(
                            challenge = challenge,
                            modifier = Modifier
                                .padding(horizontal = 0.dp)
                                .padding(vertical = 5.dp)
                                .clickable {
                                    navigator.navigate(
                                        ChallengeWorkScreenDestination(challenge.ID!!)
                                    )
                                }
                        )
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(state.isLoading) {
            CircularProgressIndicator()
        }
    }

}