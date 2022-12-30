package com.example.procod.presentation.challenge_make

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.R
import com.example.procod.presentation.destinations.ChallengeMakeScreenDestination
import com.example.procod.presentation.destinations.ProfileTabScreenDestination
import com.example.procod.presentation.profile_tab.ProfileTabScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Destination
fun ChallengeMakeScreen(
    id: Int,
    navigator: DestinationsNavigator,
    viewModel: ChallengeMakeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    
    if (!state.isLoading) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Make Challenge")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator.navigate(
                                ProfileTabScreenDestination(-1)
                            )
                        }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Menu Btn")
                        }
                    },
                    backgroundColor = colorResource(R.color.color1),
                    contentColor = Color.White,
                    elevation = 2.dp
                )
            }, content = {


        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Title",
                        modifier = Modifier
                            .padding(horizontal = 0.dp)
                            .padding(top = 16.dp)
                            .padding(bottom = 5.dp)
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
                value = state.title,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnTitleChange(it)) },
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .fillMaxWidth(),
            )

            Text(
                text = "Description",
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .padding(top = 16.dp)
                    .padding(bottom = 5.dp)
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
                value = state.description,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnDescriptionChange(it)) },
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
            )

            if (state.challenge?.ChallengeExamples != null) {
                Text(
                    text = "Examples:",
                    modifier = Modifier
                        .padding(horizontal = 0.dp)
                        .padding(top = 16.dp)
                        .padding(bottom = 5.dp)
                        .fillMaxWidth(),
                )
                LazyColumn(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .border(1.dp, colorResource(R.color.color1))

//                        .background(colorResource(R.color.color1)),


                ) {
                    items(state.challenge.ChallengeExamples.size!!) { i ->
                        val example = state.challenge.ChallengeExamples[i]
                        Column() {
                            Text(text = "Input: ${example.Ex_input!!}",
                                modifier = Modifier.padding(bottom = 3.dp).padding(start = 3.dp))
                            Text(text = "Output: ${example.Ex_output!!}",
                                modifier = Modifier.padding(bottom = 3.dp).padding(start = 3.dp))
                            Text(text = "Description: ${example.Description!!}",
                                modifier = Modifier.padding(bottom = 3.dp).padding(start = 3.dp))
                            Row(){
                            Button(
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor =Color.Yellow, contentColor = Color.Black ),
                                modifier = Modifier
                                .padding(end = 5.dp),

                                onClick = {
                                viewModel.onEvent(ChallengeMakeEvent.EditExample(
                                    id = example.ID!!,
                                    input = example.Ex_input,
                                    output = example.Ex_output,
                                    desc = example.Description
                                ))
                            }) {
                                Text(text = "Edit Example")
                            }
                            Button(

                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor =Color.Yellow, contentColor = Color.Black ),
                                onClick = { viewModel.onEvent(ChallengeMakeEvent.DeleteExample(example.ID!!)) }) {
                                Text(text = "Delete Example")
                            }
                            }
                        }
                    }
                }
            }
            Text(
                text = "Input Example",
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .padding(top = 10.dp)
                    .padding(bottom = 5.dp)
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
                value = state.ex_input,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnExInputChange(it)) },
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
            )

            Text(
                text = "Output Example",
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .padding(top = 16.dp)
                    .padding(bottom = 5.dp)
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
                value = state.ex_output,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnExOutputChange(it)) },
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
            )

            Text(
                text = "Description",
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .padding(top = 16.dp)
                    .padding(bottom = 5.dp)
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
                value = state.ex_description,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnExDescriptionChange(it)) },
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
            )
            Row(
                Modifier.fillMaxWidth().padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Button(
                    modifier = Modifier.padding(end = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor =colorResource(R.color.color1), contentColor = Color.White ),
                    onClick = { viewModel.onEvent(ChallengeMakeEvent.SubmitExample) },

                    ) {
                    Text(text = if (state.exId > 0) "Edit Example" else "Submit Example")

                }
                if (state.exId > 0) {
                    Button(
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor =colorResource(R.color.color1), contentColor = Color.White ),
                        onClick = { viewModel.onEvent(ChallengeMakeEvent.CancelExample) }) {
                        Text(text = "Cancel Edit")
                    }
                }
            }
            if (state.challenge?.ChallengeTargets != null) {
                Text(
                    text = "Targets:",
                    modifier = Modifier
                        .padding(horizontal = 0.dp)
                        .padding(top = 16.dp)
                        .padding(bottom = 5.dp)
                        .fillMaxWidth(),
                )
                LazyColumn(
                    modifier = Modifier
                        .height(100.dp)
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .border(1.dp, colorResource(R.color.color1))

                ) {
                    items(state.challenge.ChallengeTargets.size!!) { i ->
                        val target = state.challenge.ChallengeTargets[i]
                        Column() {
                            Text(
                                text = "Input: ${target.Input!!}",
                                modifier = Modifier.padding(bottom = 3.dp)
                            )
                            Text(
                                text = "Output: ${target.Target_output!!}",
                                modifier = Modifier.padding(bottom = 3.dp)
                            )
                            Row(){
                            Button(
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Yellow
                                    , contentColor = Color.Black
                                ),
                                modifier = Modifier
                                    .padding(end = 5.dp),
                                onClick = {
                                    viewModel.onEvent(
                                        ChallengeMakeEvent.EditTarget(
                                            id = target.ID!!,
                                            input = target.Input,
                                            output = target.Target_output
                                        )
                                    )
                                }) {
                                Text(text = "Edit Target")
                            }
                            Button(
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Yellow
                                    , contentColor = Color.Black
                                ),
                                modifier = Modifier
                                    .padding(end = 5.dp),
                                onClick = { viewModel.onEvent(ChallengeMakeEvent.DeleteTarget(target.ID!!)) }) {
                                Text(text = "Delete Target")
                            }
                        }
                        }
                    }
                }
            }

            Text(
                text = "Input",
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .padding(top = 16.dp)
                    .padding(bottom = 5.dp)
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
                value = state.input,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnInputChange(it)) },
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
            )

            Text(
                text = "Output",
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .padding(top = 16.dp)
                    .padding(bottom = 5.dp)
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
                value = state.output,
                onValueChange = { viewModel.onEvent(ChallengeMakeEvent.OnOutputChange(it)) },
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
            )
            Row(  Modifier.fillMaxWidth().padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {

                Button(
                    modifier = Modifier.padding(end = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor =colorResource(R.color.color1), contentColor = Color.White ),
                    onClick = { viewModel.onEvent(ChallengeMakeEvent.SubmitTarget) }) {
                    Text(text = if (state.targetId > 0) "Edit Target" else "Submit Target")
                }
                if (state.targetId > 0) {
                    Button(onClick = { viewModel.onEvent(ChallengeMakeEvent.CancelTarget) },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor =colorResource(R.color.color1), contentColor = Color.White ),) {
                        Text(text = "Cancel Edit")

                    }
                }
            }
            if (state.challenge?.ChallengeLabels != null) {

                LazyRow() {
                    items(state.challenge.ChallengeLabels.size!!) { i ->
                        val label = state.challenge.ChallengeLabels[i]
                        Text(
                            text = label.Label!!,
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .padding(top = 5.dp)
                                .background(Color.Yellow)
                                .clickable { viewModel.onEvent(ChallengeMakeEvent.MinLabel(label.ID!!)) }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .padding(vertical = 5.dp)
            ){
                Text(text = "Choose Labels: ")
            LazyRow(

            ) {
                items(state.labels.size) { i ->
                    val label = state.labels[i]
                    if (state.challenge?.ChallengeLabels == null) {

                        Text(
                            text = label.Label!!,
                            modifier = Modifier
                                .padding(end = 10.dp)
//                                .padding(top = 5.dp)
                                .clickable { viewModel.onEvent(ChallengeMakeEvent.AddLabel(label.ID!!)) }
                        )
                    } else if (!state.challenge.ChallengeLabels.contains(label)) {
                        Text(
                            text = label.Label!!,
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .clickable { viewModel.onEvent(ChallengeMakeEvent.AddLabel(label.ID!!)) }
                        )
                    }
                }
            }}
            Row(  Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
            Button(
                modifier = Modifier.padding(top = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor =colorResource(R.color.color1), contentColor = Color.White ),
                onClick = {
                viewModel.onEvent(ChallengeMakeEvent.Submit)
                navigator.navigateUp()
            }) {
                Text(text = "Submit")
            }}
        }})
    }
}