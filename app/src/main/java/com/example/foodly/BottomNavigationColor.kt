@file:Suppress("DEPRECATION")

package com.example.foodly

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController


//Composable to set the color of the bottom navigation color
@Composable
fun BottomNavigationBarColor(color: Color, darkIcons:Boolean){
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setNavigationBarColor(color = color, darkIcons = darkIcons)
    }
}