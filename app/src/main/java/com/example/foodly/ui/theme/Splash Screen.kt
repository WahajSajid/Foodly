@file:Suppress("DEPRECATION")

package com.example.foodly.ui.theme

import android.window.SplashScreen
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodly.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SplashScreen() {
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
            Text(
                "Foodly.",
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontSize = 45.sp,
                    color = Color(appThemeColor2.toArgb()),
                    fontFamily = FontFamily.Cursive
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    SplashScreen()
}