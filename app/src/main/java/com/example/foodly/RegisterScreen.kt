package com.example.foodly

import android.content.Context
import android.os.Vibrator
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2

@Composable
fun RegisterScreen(
    stateViewModel: StateViewModel = viewModel(),
    onBack: () -> Unit = {},
    onSignIn: () -> Unit = {}
) {
    StatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        TopBar(heading = "New Account", onBack = onBack)
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
        ) {
            Column {
                Text(
                    "Full Name",
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
                    modifier = Modifier.padding(start = 55.dp, top = 10.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextInputField(
                        value = stateViewModel.name.value,
                        onValueChange = { stateViewModel.name.value = it },
                        placeHolder = "Name",
                        modifier = Modifier
                            .clickable { }
                            .padding(top = 5.dp)
                            .focusRequester(stateViewModel.focusRequester1)
                            .border(
                                width = 1.dp,
                                color = Color(appThemeColor1.toArgb()),
                                shape = RoundedCornerShape(26.dp)
                            ),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { stateViewModel.focusRequester2.requestFocus() })
                    )
                }
                Text(
                    "Email",
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
                    modifier = Modifier.padding(start = 55.dp, top = 10.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextInputField(
                        value = stateViewModel.email.value,
                        onValueChange = { stateViewModel.email.value = it },
                        placeHolder = "Email",
                        modifier = Modifier
                            .clickable { }
                            .padding(top = 5.dp)
                            .focusRequester(stateViewModel.focusRequester2)
                            .border(
                                width = 1.dp,
                                color = Color(appThemeColor1.toArgb()),
                                shape = RoundedCornerShape(26.dp)
                            ),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { stateViewModel.focusRequester3.requestFocus() })
                    )
                }

                Text(
                    "Password",
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
                    modifier = Modifier.padding(start = 55.dp, top = 10.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextInputField(
                        value = stateViewModel.password.value,
                        onValueChange = { stateViewModel.password.value = it },
                        placeHolder = "Password",
                        modifier = Modifier
                            .clickable { }
                            .padding(top = 5.dp)
                            .focusRequester(stateViewModel.focusRequester4)
                            .border(
                                width = 1.dp,
                                color = Color(appThemeColor1.toArgb()),
                                shape = RoundedCornerShape(26.dp)
                            ),
                        trailingIcon = {
                            Image(
                                if (stateViewModel.showPasswordRegister.value) painterResource(
                                    R.drawable.unhide
                                ) else painterResource(R.drawable.hide),
                                contentDescription = null,
                                Modifier
                                    .size(20.dp)
                                    .clickable {
                                        stateViewModel.showPasswordRegister.value =
                                            !stateViewModel.showPasswordRegister.value
                                    }
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = null),
                        visualTransformation = if (stateViewModel.showPasswordRegister.value) VisualTransformation.None else PasswordVisualTransformation()
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "By continuing, you agree to")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Terms of Use ", color = Color(
                                appThemeColor2.toArgb()
                            ), modifier = Modifier.clickable { }
                        )
                        Text(text = "and")
                        Text(
                            text = " Privacy Policy",
                            color = Color(appThemeColor2.toArgb()),
                            modifier = Modifier.clickable { })
                    }
                    ElevatedButton(
                        onClick = {},
                        modifier = Modifier
                            .width(200.dp)
                            .padding(top = 10.dp),
                        colors = ButtonDefaults.elevatedButtonColors(
                            appThemeColor2
                        )
                    ) {
                        Text(text = "Sign Up", color = Color.White)
                    }
                    Text(text = "or sign up with", modifier = Modifier.padding(top = 5.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = {}) {
                            Image(
                                painter = painterResource(R.drawable.icons8_google),
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = {}) {
                            Image(
                                painter = painterResource(R.drawable.icons8_facebook),
                                contentDescription = null
                            )
                        }
                    }


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Already have an account? ")
                        Text(
                            text = "Log In",
                            fontWeight = FontWeight.Bold,
                            color = Color(appThemeColor2.toArgb()),
                            modifier = Modifier.clickable { onSignIn() })
                    }
                }
            }
        }
    }
}

//Function to register the user
fun registerUser(stateViewModel: StateViewModel,context: Context) {
 //
}


//Function to check input fields are empty or not
fun inputFieldsEmpty(stateViewModel: StateViewModel): Boolean {
    return (stateViewModel.name.value == "" && stateViewModel.email.value == "" && stateViewModel.password.value == "")
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    RegisterScreen()
}