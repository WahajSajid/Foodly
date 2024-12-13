@file:Suppress("DEPRECATION")

package com.example.foodly

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodly.ui.theme.AppNameHeading
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WelcomeScreen() {
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
        AppNameHeading("Foodly.")
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    WelcomeScreen()
}