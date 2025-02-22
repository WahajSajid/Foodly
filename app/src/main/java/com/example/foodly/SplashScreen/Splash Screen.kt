@file:Suppress("DEPRECATION")

package com.example.foodly.SplashScreen


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodly.R
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController = NavController(LocalContext.current)) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)
    Column(
        modifier = Modifier
            .padding(WindowInsets.systemBars.asPaddingValues())
            .fillMaxSize()
            .background(color = Color(appThemeColor1.toArgb()))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.foodly_icon),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
            //Creating an animation for the text
            val text = "Foodly."
            val displayedText = remember { mutableStateOf("") }

            //Launching an effect to start the animation
            LaunchedEffect(Unit) {
                for (char in text) {
                    displayedText.value += char
                    delay(150)
                }
            }

            AnimatedVisibility(
                visible = displayedText.value.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                AppNameHeading(
                    displayedText.value,
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 45.sp,
                        color = Color(appThemeColor2.toArgb()),
                        fontFamily = FontFamily.Cursive
                    )
                )
            }
        }
    }
}


@Composable
fun AppNameHeading(appNameDisplayed: String = "", textStyle: TextStyle = TextStyle()) {
    Text(
        text = appNameDisplayed,
        style = textStyle
    )
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    SplashScreen()
}