package com.example.procod.presentation.home_tab

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.R
import com.example.procod.presentation.destinations.ChallengeWorkScreenDestination
import com.example.procod.presentation.destinations.ProfileTabScreenDestination
import com.example.procod.presentation.home_tab.components.ChallengeCard
import com.example.procod.presentation.home_tab.components.UserCard
import com.example.procod.presentation.login.LoginEvent
import com.example.procod.util.BottomNavBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import okhttp3.internal.connection.RouteDatabase

@Composable
@Destination
fun HomeTabScreen(
    id: Int,
    navigator: DestinationsNavigator,
    viewModel: HomeTabViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)

    Scaffold(
        bottomBar = {
            BottomNavBar(navigator = navigator)
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = it.calculateBottomPadding())
//            .background(color= colorResource(R.color.color1))
        ) {
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.onEvent(HomeTabEvent.Refresh) }) {


                Column(modifier = Modifier
                    .background(color = colorResource(R.color.color5))
//                .padding(16.dp)
                ) {

                    Card(modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .background(color = colorResource(R.color.color5))

                    ) {
                        Column(modifier = Modifier
                            .fillMaxWidth()) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .background(color = colorResource(R.color.color5))
//                            .padding(bottom = 20.dp)
                                ,
                                horizontalArrangement = Arrangement.SpaceBetween

                            ) {
                                Column(modifier = Modifier
                                    .background(color= colorResource(R.color.color5))
                                    , horizontalAlignment = Alignment.CenterHorizontally

                                ) {
                                    Text(text =state.username, fontWeight = FontWeight.Bold,fontSize = 30.sp, color = Color.White )
                                    Text(text =state.email, color = Color.White )
                                }
                                Image(painter = painterResource(id = R.drawable.ic_baseline_account_circle_24_white,),
                                    contentDescription ="Picture" ,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clickable {
                                            navigator.navigate(
                                                ProfileTabScreenDestination(-1)
                                            )
                                        }
                                )

                            }

                            Column(modifier = Modifier
                                .background(color = colorResource(R.color.color5))
                                .padding(vertical = 20.dp)
                            ) {
                                Card(shape = RoundedCornerShape(30), modifier = Modifier
                                    .padding(vertical = 0.dp)
                                    .background(colorResource(R.color.color5))
                                ) {
                                    Row(modifier = Modifier
                                        .padding(0.dp)
                                        .background(color = colorResource(R.color.color3))
                                        .padding(10.dp),
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier
                                                .weight(1f)
                                        ) {
                                            Text(
                                                text = "Challenge Attempted",
                                                textAlign = TextAlign.Center,
                                                color = Color.White
                                            )
                                            Text(
                                                text = state.chalAttempt.toString(),
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier
                                                .weight(1f)
                                        ) {
                                            Text(
                                                text = "Challenge Completed",
                                                textAlign = TextAlign.Center,
                                                color = Color.White
                                            )
                                            Text(
                                                text = state.chalComplete.toString(),
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Column(
                                            verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier
                                                .weight(1f)

                                        ) {
                                            Text(
                                                text = "Challenge Made",
                                                textAlign = TextAlign.Center,
                                                color = Color.White
                                            )
                                            Text(
                                                text = state.chalMade.toString(),
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }}}
                        }
                    }


                }
            }
            Column(modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 10.dp)) {

                Text(
                    text = "Tips & Tricks",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Card(shape = RoundedCornerShape(10),
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .height(80.dp),
                    backgroundColor = colorResource(R.color.color3)
                ) {


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(R.color.color3)),
                    ) {
                        Column(

                        ) {
                            Text(
                                text = "Logic First",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .padding(top = 5.dp)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Focus on the logic of your program first before starting to code.",
                                    color = Color.White,
                                    fontWeight = FontWeight.Light,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 2,
                                    fontSize =11.sp,
                                    modifier = Modifier
//                                        .width(90.dp)
                                        .padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = "Challenges",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                LazyRow(
                    modifier = Modifier
                        .padding(top = 10.dp)
                ) {
                    items(state.challenges.size) { i ->
                        val challenge = state.challenges[i]
                        ChallengeCard(
                            challenge = challenge,
                            modifier = Modifier.clickable {
                                navigator.navigate(
                                    ChallengeWorkScreenDestination(challenge.ID!!)
                                )
                            }

                        )
                    }
                }}



            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column() {

                    Text(
                        text = "Leaderboard",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
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
                        label = { Text(text = "Search", fontSize = 12.sp, modifier = Modifier.padding(bottom = 0.dp))},
                        value = state.searchQuery,
                        onValueChange = { viewModel.onEvent(HomeTabEvent.OnSearchQueryChange(it)) },
                        modifier = Modifier
                            .padding(7.dp)
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(10)

                    )
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                        .padding(top = 5.dp)
                        .padding(bottom = 3.dp)
                        , verticalAlignment = Alignment.CenterVertically

                    ) {
                        Text(text = "Rank",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(text = "Profile",
                            modifier = Modifier.weight(3f),
                            textAlign = TextAlign.Center
                        )
                        Text(text = "Challenge Completed",
                            modifier = Modifier.weight(2f),
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(250.dp, 250.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        items(state.users.size) { i ->
                            UserCard(user = state.users[i], modifier = Modifier,i+1,state)

                        }
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