package com.example.foodly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModelProvider
import com.example.foodly.ui.theme.FoodlyTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val stateViewModel = ViewModelProvider(this)[StateViewModel::class.java]
            FoodlyTheme {
                LaunchedEffect(Unit) {
                    delay(2000)
                    stateViewModel.showSplash.value = false
                }
                AnimatedVisibility(visible = stateViewModel.showSplash.value) {
                    SplashScreen()
                }
                AnimatedVisibility(visible = !stateViewModel.showSplash.value, enter = slideInHorizontally()) {
                    WelcomeScreen()
                }
            }
        }
    }
}


