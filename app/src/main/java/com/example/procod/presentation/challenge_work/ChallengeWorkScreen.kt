package com.example.procod.presentation.challenge_work

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.R
import com.example.procod.presentation.challenge_tab.ChallengeTabEvent
import com.example.procod.presentation.destinations.ProfileTabScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Destination
fun ChallengeWorkScreen(
    id: Int,
    navigator: DestinationsNavigator,
    viewModel: ChallengeWorkViewModel = hiltViewModel()
) {
    val state = viewModel.state
    if (!state.isLoading) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = state.challenge?.Title!!)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator.navigateUp()
//                            navigator.navigate(
//                                ProfileTabScreenDestination(-1)
//                            )
                        }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Btn")
                        }
                    },
                    backgroundColor = colorResource(R.color.color1),
                    contentColor = Color.White,
                    elevation = 2.dp
                )
            }, content = {
        Column(modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ) {
//            Text(
//                text = state.challenge?.Title!!
//            )
            Text(
                text = "Challenge Description", fontWeight = FontWeight.Bold, fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = state.challenge?.Description!!,  modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .heightIn(0.dp, 120.dp)
                    .verticalScroll(rememberScrollState())


            )
            // input output example
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .background(color = colorResource(R.color.color3))) {
                Column(modifier = Modifier.background(color = colorResource(R.color.color4))) {
                    Row(modifier = Modifier.background(color = colorResource(R.color.color3))) {

                        if (state.challenge.ChallengeExamples!!.size > 0) {
                            Text(
                                text = "Input & Output Example:",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                LazyColumn(modifier = Modifier
                    .heightIn(0.dp, 80.dp)
                    .background(color = colorResource(R.color.color4))) {
                    items(state.challenge.ChallengeExamples!!.size) { i ->
                        val example = state.challenge.ChallengeExamples[i]
                        Row(modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth()
                            .heightIn(0.dp, 50.dp), horizontalArrangement = Arrangement.Start) {
                            Text(text = "Input: ${example.Ex_input!!}", modifier = Modifier.padding(horizontal = 10.dp), color = Color.White)
                            Text(text = "Output: ${example.Ex_output!!}", color = Color.White)

                        }

                    }}
                }}

            // tulis code
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .background(color = colorResource(R.color.color2))) {
                Column() {
                    Row( horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(color = colorResource(R.color.color1))){

                        Text(text = "Your Code:", fontWeight = FontWeight.Bold, color = Color.White ,modifier = Modifier
                            .padding(8.dp))
                        Row(modifier = Modifier
                            .padding(8.dp)) {
                            Icon(
                                Icons.Rounded.PlayArrow,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .clickable {   viewModel.onEvent(ChallengeWorkEvent.Submit)  },
                                tint = Color.White
                            )
                            Icon(
                                Icons.Rounded.Refresh,
                                contentDescription = "",
                                modifier = Modifier.clickable {   viewModel.onEvent(ChallengeWorkEvent.Reset)  },
                                tint = Color.White
                            )
                        }
                    }
                TextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledTextColor = Color.Transparent,
                        placeholderColor = Color.White,
                        disabledPlaceholderColor= Color.Black,
                        disabledLabelColor = Color.Black,
                        errorLabelColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        cursorColor = Color.Black,
                        errorCursorColor = Color.Black,
                        focusedBorderColor = colorResource(R.color.color1),
                        textColor = Color.White
                        ),
                    value = state.code,
                    onValueChange = { viewModel.onEvent(ChallengeWorkEvent.OnCodeChange(it)) },
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 250.dp)
                        .fillMaxSize()
                        .background(color = colorResource(R.color.color2))
//                        .verticalScroll(rememberScrollState())
                )}}

// tulis code
//            Row() {
//                Button(onClick = {
//                    viewModel.onEvent(ChallengeWorkEvent.Submit)
//                }) {
//                    Text(text = "Submit")
//                }
//                Button(onClick = { viewModel.onEvent(ChallengeWorkEvent.Reset) }) {
//                    Text(text = "Reset")
//                }
//            }

            // code summary

            if (state.result != null) {
                Column() {


                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(color = colorResource(R.color.color2))) {
                Column(modifier = Modifier.background(color = colorResource(R.color.color3))) {
                    Row(modifier = Modifier.background(color = colorResource(R.color.color2))) {

                        Text(
                            text = "Code Summary",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        )

                    }

                    Text(
                        text = "Sdout: ${state.result.stdout} ",
                        color = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(vertical = 1.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Runtime: ${state.result.time} ",
                        color = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(vertical = 1.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = "Memory: ${state.result.memory}",
                        color = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(vertical = 1.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Description: ${state.result.status!!.description}",
                        color = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(vertical = 1.dp)
                            .fillMaxWidth()
                    )
                }}
    //code summary

                    // result

                    Card(Modifier.padding(vertical = 8.dp), shape = RoundedCornerShape(5)) {


                if (state.result.status?.id!! > 4) {
                    Text(text = "Result: Error; Incorrect Syntax",
                        Modifier
                            .fillMaxWidth()
                            .background(color = colorResource(R.color.color2))
                            .padding(5.dp),
                        color =Color.White,

                    )
                }  else if (state.result.status.id == 3 && state.result.stdout == "${state.challenge.ChallengeTargets?.get(0)!!.Target_output!!}\n") {
                    Text(text = "Result: Correct!", Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(R.color.color2))
                        .padding(5.dp),
                        color =Color.White,)
                } else if (state.result.status.id == 3) {
                    Text(text = "Result: Incorrect!", Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(R.color.color2))
                        .padding(5.dp),
                        color =Color.White,)
                }}
                // result

            }}
        }})
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(state.isLoading) {
            CircularProgressIndicator(color = colorResource(R.color.color1))
        }
    }
}