package com.example.procod.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.presentation.destinations.*
import com.example.procod.util.AuthResult
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { result ->
            when(result) {
                is AuthResult.Authorized -> {
                    navigator.navigate(ChallengeTabScreenDestination(0)) { //Ubah ProfileTabScreenDestination ke {isi iki}ScreenDestination sg kon mau liat
                        popUpTo(LoginScreenDestination.route) {
                            inclusive = true
                        }
                    }
                }
                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "You're not authorized",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "An unknown error occurred",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (state.isLogin) "LOGIN" else "REGISTER"
        )
        if (!state.isLogin) {
            Text(
                text = "Username"
            )
            OutlinedTextField(
                value = state.username,
                onValueChange = { viewModel.onEvent(LoginEvent.OnUsernameChange(it)) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            )
        }
        Text(
            text = "Email"
        )
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(LoginEvent.OnEmailChange(it)) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
        Text(
            text = "Password"
        )
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(LoginEvent.OnPasswordChange(it)) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
        Button(
            onClick = {
                viewModel.onEvent(LoginEvent.ButtonClick)
            }
        ) {
            Text(
                text = if (state.isLogin) "Log In" else "Register"
            )
        }
        Row(){
            Text(
                text = if (state.isLogin) "Don't have an account?" else "Already have an account?"
            )
            ClickableText(
                text = AnnotatedString(if (state.isLogin) "Register" else "Log In"),
                onClick = { viewModel.onEvent(LoginEvent.Switch) }
            )
        }
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}