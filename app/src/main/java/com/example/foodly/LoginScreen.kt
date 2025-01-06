@file:Suppress("DEPRECATION")

package com.example.foodly

import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodly.ui.theme.appThemeColor1
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LogInScreen(stateViewModel: StateViewModel = StateViewModel()) {

    //Setting the app bar theme color
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Color(appThemeColor1.toArgb()),
            darkIcons = true
        )
        systemUiController.setStatusBarColor(
            color = Color(appThemeColor1.toArgb()),
            darkIcons = true
        )
    }
    Box(
        modifier = Modifier
            .padding(WindowInsets.statusBars.asPaddingValues())
            .fillMaxSize()
    ) {
        TopBar(heading = "Log In")
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Text(
                    "Welcome!",
                    style = TextStyle(
                        color = Color.Black,
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
                        .padding(top = 35.dp)
                ) {
                    TextInputField(
                        stateViewModel = stateViewModel,
                        purpose = "email",
                        icon = painterResource(R.drawable.baseline_attach_email_24),
                        placeHolder = "Email",
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .focusRequester(stateViewModel.focusRequester1.Default)
                            .border(
                                width = 1.dp,
                                color = Color(appThemeColor1.toArgb()),
                                shape = RoundedCornerShape(26.dp)
                            )
                    )
                    TextInputField(
                        stateViewModel = stateViewModel,
                        purpose = "password",
                        icon = painterResource(R.drawable.baseline_attach_email_24),
                        placeHolder = "Password",
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .focusRequester(stateViewModel.focusRequester1.Default)
                            .border(
                                width = 1.dp,
                                color = Color(appThemeColor1.toArgb()),
                                shape = RoundedCornerShape(26.dp)
                            )
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true, name = "Log In Screen")
@Composable
private fun Preview() {
    LogInScreen()
}