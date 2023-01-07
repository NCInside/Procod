package com.example.procod.presentation.profile_tab

import android.app.Activity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.R
import com.example.procod.presentation.challenge_work.ChallengeWorkEvent
import com.example.procod.presentation.destinations.ChallengeMakeScreenDestination
import com.example.procod.presentation.destinations.ChallengeWorkScreenDestination
import com.example.procod.util.BottomNavBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun ProfileTabScreen(
    id: Int,
    navigator: DestinationsNavigator,
    viewModel: ProfileTabViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val activity = LocalContext.current as? Activity

    Scaffold(
        bottomBar = {
            BottomNavBar(navigator = navigator)
        }
    ) {
        if (!state.isLoading) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(bottom = it.calculateBottomPadding())
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)

                ) {
                    Text(
                        text = "My Profile",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    Image(painter = painterResource(id = R.drawable.ic_baseline_account_circle_24,),
                        contentDescription ="Picture" ,
                        modifier = Modifier.size(150.dp)
                    )

                    Text(
                        text = state.username,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = state.email,
                    )

                    Card(shape = RoundedCornerShape(30), modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Row(modifier = Modifier
                            .padding(0.dp)
                            .background(color = colorResource(R.color.color1))
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
                        }}
                }
                Text(
                    text = "Challenges Made", fontWeight = FontWeight.Bold, fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(start = 5.dp)
                )
                Column(modifier = Modifier) {
                    LazyColumn(modifier = Modifier
                        .heightIn(0.dp, 190.dp)) {
                        items(state.challenges.size) { i ->
                            val challenge = state.challenges[i]
                            com.example.procod.presentation.profile_tab.Components.ChallengeItem(
                                challenge = challenge,
                                modifier = Modifier
                                    .padding(horizontal = 0.dp)
                                    .padding(vertical = 5.dp)
                                    .clickable {
                                        navigator.navigate(
                                            ChallengeMakeScreenDestination(challenge.ID!!)
                                        )
                                    }
                            )
                        }
                    }}
//            Column() {
//                Button(onClick = {
//                    viewModel.onEvent(ProfileTabEvent.DeleteProfile)
//                    activity?.finish()
//                }) {
//                    Text(text = "Delete Profile")
//                }
//                Button(onClick = {
//                    viewModel.onEvent(ProfileTabEvent.Logout)
//                    activity?.finish()
//                }) {
//                    Text(text = "Logout")
//                }
//                Button(onClick = {
//                    viewModel.onEvent(ProfileTabEvent.EditProfile)
//                }) {
//                    Text(text = "Edit Profile")
//                }
//            }

                Text(
                    text = "Profile Settings", fontWeight = FontWeight.Bold, fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .padding(start = 5.dp)
                )
                Card(
                    shape = RoundedCornerShape(10),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)){
                    Column(modifier = Modifier.background(color = colorResource(R.color.grayl))) {
                        Row(modifier = Modifier.background(color = colorResource(R.color.grayl))) {

//                        Text(
//                            text = "Profile",
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Black,
//                            modifier = Modifier
//                                .padding(8.dp)
//                                .fillMaxWidth()
//                        )

                        }

                        Row(  modifier = Modifier
                            .padding(8.dp)
                            .padding(start = 10.dp)
                            .padding(top = 5.dp)
                            .clickable { viewModel.onEvent(ProfileTabEvent.EditProfile) },){
                            Icon(
                                Icons.Rounded.AccountCircle,
                                contentDescription = "",
                                modifier = Modifier.clickable {   viewModel.onEvent(ProfileTabEvent.EditProfile) },
                                tint = Color.Black
                            )
                            Text(
                                text = "Edit Profile",
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .padding(vertical = 4.dp)
                                    .fillMaxWidth()
                                    .clickable { viewModel.onEvent(ProfileTabEvent.EditProfile) }
                            )
                        }
                        Row(  modifier = Modifier
                            .padding(8.dp)
                            .padding(start = 10.dp)
                            .clickable { viewModel.onEvent(ProfileTabEvent.Logout) }){
                            Icon(
                                Icons.Rounded.ExitToApp,
                                contentDescription = "",
                                modifier = Modifier.clickable {   viewModel.onEvent(ProfileTabEvent.Logout) },
                                tint = Color.Black
                            )
                            Text(
                                text = "Log Out",
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .padding(vertical = 4.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.onEvent(ProfileTabEvent.Logout)
                                        activity?.finish()
                                    }
                            )
                        }
                        Row(  modifier = Modifier
                            .padding(8.dp)
                            .padding(start = 10.dp)
                            .clickable { viewModel.onEvent(ProfileTabEvent.DeleteProfile) },){
                            Icon(
                                Icons.Rounded.Delete,
                                contentDescription = "",
                                modifier = Modifier.clickable {   viewModel.onEvent(ProfileTabEvent.DeleteProfile)
                                    activity?.finish()
                                                              },
                                tint = Color.Black
                            )
                            Text(
                                text = "Delete Profile",
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .padding(vertical = 4.dp)
                                    .fillMaxWidth()
                                    .clickable { viewModel.onEvent(ProfileTabEvent.DeleteProfile)
                                        activity?.finish()}
                            )
                        }


                    }}


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




    // edit profile
    if(state.isEditing) {
    Card(
        shape = RoundedCornerShape(0),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp),

    ) {

            Column(
                Modifier
                    .background(color = Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            )

        {

                Text(
                    text = "Edit Profile", fontWeight = FontWeight.Bold, fontSize = 24.sp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(start = 5.dp)
                )
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledTextColor = Color.Transparent,
                        placeholderColor = Color.Black,
                        disabledPlaceholderColor= Color.Black,
                        disabledLabelColor = Color.Black,
                        errorLabelColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        cursorColor = Color.Black,
                        errorCursorColor = Color.Black,
                        focusedBorderColor = colorResource(R.color.color1),

                        ),
                    value = state.username,
                    label = { Text(text = "Username")},
                    onValueChange = { viewModel.onEvent(ProfileTabEvent.OnNameChange(it)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                )
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledTextColor = Color.Transparent,
                        placeholderColor = Color.Black,
                        disabledPlaceholderColor= Color.Black,
                        disabledLabelColor = Color.Black,
                        errorLabelColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        cursorColor = Color.Black,
                        errorCursorColor = Color.Black,
                        focusedBorderColor = colorResource(R.color.color1),

                        ),
                    value = state.email,
                    label = { Text(text = "Email")},
                    onValueChange = { viewModel.onEvent(ProfileTabEvent.OnEmailChange(it)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                )
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledTextColor = Color.Transparent,
                        placeholderColor = Color.Black,
                        disabledPlaceholderColor= Color.Black,
                        disabledLabelColor = Color.Black,
                        errorLabelColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        cursorColor = Color.Black,
                        errorCursorColor = Color.Black,
                        focusedBorderColor = colorResource(R.color.color1),

                        ),
                    value = state.password,
                    label = { Text(text = "Password")},
                    onValueChange = { viewModel.onEvent(ProfileTabEvent.OnPassChange(it)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                )
                Row() {
                    Button(
                        onClick = { viewModel.onEvent(ProfileTabEvent.CancelProfile) },
                        modifier = Modifier
                            .width(100.dp)
                            .padding(end = 10.dp)
                            .size(40.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                    ) {
                        Text(text = "Cancel", color = Color.Black)
                    }
                    Button(
                        onClick = { viewModel.onEvent(ProfileTabEvent.SubmitProfile) },
                        modifier = Modifier
                            .width(100.dp)
                            .padding(end = 10.dp)
                            .size(40.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.color1)),
                    ) {
                        Text(text = "Submit", color = Color.White)


                    }
                }
            }
        }
    }
    // edit profile


}