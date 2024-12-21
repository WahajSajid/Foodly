package com.example.foodly

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodly.ui.theme.FoodlyTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import kotlinx.coroutines.delay

@Suppress("DEPRECATION")
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val stateViewModel = ViewModelProvider(this)[StateViewModel::class.java]
            val context = LocalContext.current
            val navController = rememberNavController()
            AnimatedNavHost(navController = navController, startDestination = "SplashScreen") {
                composable(
                    route = "SplashScreen"
                ) { SplashScreen(navController = navController) }
                composable(
                    route = "WelcomeScreen",
                    enterTransition = { slideInHorizontally{it} }) {
                    WelcomeScreen(
                        navController = navController,
                        onSignIn = { navController.navigate("OnBoardingScreen") })
                }
                composable(
                    route = "OnBoardingScreen",
                    enterTransition = { slideInHorizontally{-it} }) {
                    OnBoardingScreen(
                        navController = navController
                    )
                }
            }
            FoodlyTheme {
                LaunchedEffect(Unit) {
                    delay(2000)
                    navController.navigate("WelcomeScreen")
                }
            }
            BackHandler {
                val currentDestination = navController.currentBackStackEntry?.destination?.route
                if (currentDestination == "WelcomeScreen") {
                    (context as Activity).finish()
                } else {
                    navController.navigateUp()
                }
            }
        }

    }
}


