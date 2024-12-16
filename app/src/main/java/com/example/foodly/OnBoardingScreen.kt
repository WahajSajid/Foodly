@file:Suppress("DEPRECATION")

package com.example.foodly

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodly.ui.theme.appThemeColor1
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun OnBoardingScreen() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)
    Column(
        modifier = Modifier
            .padding(WindowInsets.systemBars.asPaddingValues())
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.home_made_food), contentDescription = "Order for food",
        )
    }
}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    OnBoardingScreen()
}