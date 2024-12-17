@file:Suppress("DEPRECATION")

package com.example.foodly

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.coroutineScope

@Composable
fun WelcomeScreen(stateViewModel: StateViewModel) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color(appThemeColor2.toArgb()), darkIcons = true)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(appThemeColor2.toArgb()))
            .padding(
                WindowInsets.systemBars.asPaddingValues()
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.foodly_icon),
            contentDescription = "app icon",
            modifier = Modifier.size(100.dp)
        )
        AppNameHeading(
            "Foodly.", textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 45.sp,
                color = Color(appThemeColor1.toArgb()),
                fontFamily = FontFamily.Cursive
            )
        )
        Text(
            text = "Fresh Home Meals, Just a Tap Away!",
            modifier = Modifier.padding(top = 15.dp),
            style = TextStyle(color = Color.White)
        )
        Text(
            text = "Go ahead, and explore more. ",
            style = TextStyle(color = Color.White)
        )
        //LogIn Button
        ElevatedButton(
            onClick = { stateViewModel.showOnBoardingScreen.value = true },
            modifier = Modifier
                .width(170.dp)
                .padding(top = 50.dp),
            colors = ButtonDefaults.elevatedButtonColors(colorResource(R.color.app_theme_color_1))
        ) {
            Text(
                text = "Log In",
                style = TextStyle(color = Color(appThemeColor2.toArgb()), fontSize = 20.sp)
            )
        }
        if (stateViewModel.showOnBoardingScreen.value) {
            stateViewModel.showWelcomeScreen.value = !stateViewModel.showWelcomeScreen.value
            AnimatedVisibility(visible = true, enter = expandIn()) {
                OnBoardingScreen()
            }
        }
        //SignUp Button
        ElevatedButton(
            onClick = {},
            modifier = Modifier
                .width(170.dp)
                .padding(top = 5.dp),
            colors = ButtonDefaults.elevatedButtonColors(colorResource(R.color.white))
        ) {
            Text(
                text = "Sign Up",
                style = TextStyle(color = Color(appThemeColor2.toArgb()), fontSize = 20.sp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    WelcomeScreen(StateViewModel())
}