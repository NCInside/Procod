package com.example.procod.presentation.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.procod.R
import com.example.procod.model.Label
import com.example.procod.presentation.destinations.*
import com.example.procod.util.AuthResult
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Destination(start = true)
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {  Scaffold(backgroundColor = colorResource(R.color.color1)) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel, context) {

        viewModel.authResults.collect { result ->
            when(result) {
                is AuthResult.Authorized -> {
                    navigator.navigate(HomeTabScreenDestination (0)) { //Ubah ProfileTabScreenDestination ke {isi iki}ScreenDestination sg kon mau liat
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
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.procode_white),
            contentDescription =" Logo" ,
            modifier = Modifier.weight(1f)
        )


        Card(
            Modifier
                .weight(2f)
                .fillMaxSize()
                .padding(10.dp).verticalScroll(rememberScrollState()),
            shape = RoundedCornerShape(32.dp)
        ) {


        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 0.dp).padding(horizontal = 10.dp), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = if (state.isLogin) "LOG IN" else "REGISTER", fontWeight = FontWeight.Bold,
                fontSize = 32.sp)

            Column(Modifier.fillMaxSize(), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center)
            {

            if (!state.isLogin) {
                Text(
                    text = "Username", Modifier.padding(top = 7.dp)
                )
                OutlinedTextField(
                    value = state.username,
                    label = { Text(text = "Username")},
                    onValueChange = { viewModel.onEvent(LoginEvent.OnUsernameChange(it)) },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                )
            }
            Text(
                text = "Email", Modifier.padding(top = 7.dp)
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
                onValueChange = { viewModel.onEvent(LoginEvent.OnEmailChange(it)) },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
            )
            Text(
                text = "Password", Modifier.padding(top = 7.dp)
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
                onValueChange = { viewModel.onEvent(LoginEvent.OnPasswordChange(it)) },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
            )
            Button(
                onClick = {
                    viewModel.onEvent(LoginEvent.ButtonClick)

                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(vertical = 10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor =colorResource(R.color.color1) )
            ) {
                Text(
                    text = if (state.isLogin) "Log In" else "Register",
                    color = Color.White
                )
            }
            Row(){
                Text(
                    text = if (state.isLogin) "Don't have an account? " else "Already have an account? ",
                    fontWeight = FontWeight.Bold
                )
                ClickableText(
                    text = AnnotatedString(if (state.isLogin) "Register" else "Log In"),
                    onClick = { viewModel.onEvent(LoginEvent.Switch) },

                )
            }
        }}
        }
//        Text(
//            text = if (state.isLogin) "LOGIN" else "REGISTER"
//        )

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
}}



