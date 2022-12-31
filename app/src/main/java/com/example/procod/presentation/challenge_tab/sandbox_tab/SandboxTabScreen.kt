package com.example.procod.presentation.challenge_tab.sandbox_tab

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.R
import com.example.procod.presentation.challenge_work.ChallengeWorkEvent
import com.example.procod.util.BottomNavBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Destination
fun SandboxTabScreen(
    id: Int,
    navigator: DestinationsNavigator,
    viewModel: SandboxTabViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.color1),
                title = {
                    Text(text = "Sandbox", color = Color.White)
                }
            )
        },
        bottomBar = {
                 BottomNavBar(navigator = navigator)
        },
        content = {
    Column(modifier = Modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
    ) {
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
                    onValueChange = { viewModel.onEvent(SandboxTabEvent.OnCodeChange(it)) },
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 250.dp)
                        .fillMaxSize()
                        .background(color = colorResource(R.color.color2))
//                        .verticalScroll(rememberScrollState())
                )
            }}

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(color = colorResource(R.color.color2))) {
            Column(modifier = Modifier.background(color = colorResource(R.color.color3))) {
                Row( horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(color = colorResource(R.color.color1))){

                    Text(text = "Input:", fontWeight = FontWeight.Bold, color = Color.White ,modifier = Modifier
                        .padding(8.dp))
                    Row(modifier = Modifier
                        .padding(8.dp)) {
                        Icon(
                            Icons.Rounded.PlayArrow,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(end = 8.dp)

                                .clickable {   viewModel.onEvent(SandboxTabEvent.Run)  },
                            tint = Color.White
                        )
                        Icon(
                            Icons.Rounded.Refresh,
                            contentDescription = "",
                            modifier = Modifier.clickable {   viewModel.onEvent(SandboxTabEvent.Reset)  },
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
                        focusedBorderColor = Color.Transparent,
                        textColor = Color.White
                    ),
                    value = state.input,
                    onValueChange = { viewModel.onEvent(SandboxTabEvent.OnInputChange(it)) },
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .heightIn(40.dp, 50.dp)
                        .background(color = colorResource(R.color.color2))
                )

            }}



        if (state.result != null) {
            Column() {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(color = colorResource(R.color.color2))) {
                    Column(modifier = Modifier.background(color = colorResource(R.color.color3))) {
                        Row(modifier = Modifier.background(color = colorResource(R.color.color2))) {

                            Text(
                                text = "Output",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            )

                        }

                        Text(
                            text = " ${state.result.stdout} ",
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(vertical = 8.dp)
                                .fillMaxWidth()
                        )

                    }}
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
                                    .padding(12.dp)
                                    .fillMaxWidth()
                            )

                        }

//                        Text(
//                            text = "Sdout: ${state.result.stdout} ",
//                            color = Color.White,
//                            modifier = Modifier
//                                .padding(horizontal = 8.dp)
//                                .padding(vertical = 1.dp)
//                                .fillMaxWidth()
//                        )
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
                                .padding(bottom = 5.dp)
                                .fillMaxWidth()
                        )
                    }}
                //code summary

                // result

                // result

            }}
    }})

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(state.isLoading) {
            CircularProgressIndicator(color = colorResource(R.color.color1))
        }
    }

}



