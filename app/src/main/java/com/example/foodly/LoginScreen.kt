package com.example.foodly

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2

@Composable
fun LogInScreen(
    stateViewModel: StateViewModel = StateViewModel(),
    onSignUp: () -> Unit = {},
    onBack: () -> Unit = {},
    onGoogleSignIn: () -> Unit = {},
    onFacebookSignIn: () -> Unit = {},
    snackBarHostState:SnackbarHostState
) {
    StatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)
    Box(
        modifier = Modifier
            .padding(WindowInsets.statusBars.asPaddingValues())
            .fillMaxSize()
    ) {


        TopBar(heading = "Log In", onBack = onBack)
        //Showing SnackBar when the registration is successful
        if(stateViewModel.isOperationSuccessful.value){
            showSnackBar(hostState = snackBarHostState,stateViewModel,"Registration Successful")
        }
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Text(
                    "Welcome!",
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp
                    ),
                    modifier = Modifier.padding(start = 20.dp)
                )
                Text(
                    text = "Your information is safe with us. We value your privacy and ensure strict security measures.",
                    modifier = Modifier.padding(start = 20.dp, top = 5.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp)
                ) {
                    TextInputField(
                        leadingIcon = {
                            Icon(
                                painterResource(R.drawable.baseline_attach_email_24),
                                contentDescription = null
                            )
                        },
                        placeHolder = "Email",
                        modifier = Modifier
                            .clickable { }
                            .padding(top = 25.dp)
                            .focusRequester(stateViewModel.focusRequester1)
                            .border(
                                width = 1.dp,
                                color = Color(appThemeColor1.toArgb()),
                                shape = RoundedCornerShape(26.dp)
                            ),
                        visualTransformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { stateViewModel.focusRequester2.requestFocus() }),
                        onValueChange = { stateViewModel.email.value = it },
                        value = stateViewModel.email.value
                    )
                    TextInputField(
                        leadingIcon = {
                            Icon(
                                painterResource(R.drawable.baseline_admin_panel_settings_24),
                                contentDescription = null
                            )
                        },
                        trailingIcon = {
                            Image(
                                if (stateViewModel.showPasswordLogin.value) painterResource(R.drawable.unhide) else painterResource(
                                    R.drawable.hide
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        stateViewModel.showPasswordLogin.value =
                                            !stateViewModel.showPasswordLogin.value
                                    }
                            )
                        },
                        placeHolder = "Password",
                        modifier = Modifier
                            .clickable { }
                            .padding(top = 25.dp)
                            .focusRequester(stateViewModel.focusRequester2)
                            .border(
                                width = 1.dp,
                                color = Color(appThemeColor1.toArgb()),
                                shape = RoundedCornerShape(26.dp)
                            ),
                        visualTransformation = if (stateViewModel.showPasswordLogin.value) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onNext = null),
                        onValueChange = { stateViewModel.password.value = it },
                        value = stateViewModel.password.value
                    )
                    ElevatedButton(
                        onClick = {}, colors = ButtonDefaults.elevatedButtonColors(
                            appThemeColor2
                        ),
                        modifier = Modifier
                            .width(180.dp)
                            .padding(top = 30.dp)
                    ) {
                        Text(text = "Log In", color = Color.White)
                    }
                    Column(
                        modifier = Modifier.padding(top = 25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "or sign up with")
                        Row(modifier = Modifier.padding(top = 5.dp)) {

                            //Google Login Button
                            IconButton(onClick = onGoogleSignIn) {
                                Image(
                                    painterResource(R.drawable.icons8_google),
                                    contentDescription = "google icon"
                                )
                            }
                            //Facebook Login Button
                            IconButton(onClick = onFacebookSignIn) {
                                Image(
                                    painterResource(R.drawable.icons8_facebook),
                                    contentDescription = "facebook icon"
                                )
                            }
                        }
                        Row(modifier = Modifier.padding(top = 25.dp)) {
                            Text(text = "Don't have an account? ")
                            Text(
                                text = "Sign Up", style = TextStyle(
                                    fontWeight = FontWeight.Bold, color = Color(
                                        appThemeColor2.toArgb()
                                    )
                                ),
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .clickable { onSignUp() }
                            )
                        }
                    }
                }
            }
        }
    }
}