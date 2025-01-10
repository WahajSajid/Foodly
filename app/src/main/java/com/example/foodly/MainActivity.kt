package com.example.foodly

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodly.ui.theme.FoodlyTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
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

            FoodlyTheme {
                AnimatedNavHost(
                    navController = navController,
                    startDestination = "SplashScreen",
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(
                        route = "SplashScreen",
                        enterTransition = { slideInHorizontally { it } },
                        exitTransition = { slideOutHorizontally { -it } }
                    ) {
                        SplashScreen(navController = navController)
                        LaunchedEffect(Unit) {
                            delay(2000)
                            navController.navigate("WelcomeScreen") {
                                //
                            }
                        }
                    }
                    composable(
                        route = "WelcomeScreen",
                        enterTransition = { slideInHorizontally { it } },
                        exitTransition = { slideOutHorizontally { -it } }
                    ) {
                        WelcomeScreen(
                            navController = navController,
                            onSignIn = {
                                stateViewModel.welcomeScreenButtonClicked.value = "SignIn"
                                navController.navigate("OnBoardingScreen")
                            },
                            onSignUp = {
                                stateViewModel.welcomeScreenButtonClicked.value = "SignUp"
                                navController.navigate("OnBoardingScreen")
                            },
                            stateViewModel = stateViewModel,
                        )
                    }
                    composable(
                        route = "OnBoardingScreen",
                        enterTransition = { slideInHorizontally { -it } },
                        exitTransition = { slideOutHorizontally { it } }
                    ) {
                        OnBoardingScreen(
                            onSkip = {
                                if(stateViewModel.welcomeScreenButtonClicked.value == "SignIn") navController.navigate("LogInScreen")
                                else navController.navigate("RegisterScreen")
                            }, stateViewModel = stateViewModel, navController = navController
                        )
                    }
                    composable(
                        route = "LogInScreen",
                        enterTransition = { slideInHorizontally { -it } },
                        exitTransition = { slideOutHorizontally { it } }
                    ) {
                        LogInScreen(
                            stateViewModel = stateViewModel,
                            onSignUp = { navController.navigate("RegisterScreen") },
                            onBack = { navController.navigateUp() })
                    }
                    composable(
                        route = "RegisterScreen",
                        enterTransition = { slideInHorizontally { -it } },
                        exitTransition = { slideOutHorizontally { it } }
                    ) {
                        RegisterScreen(
                            stateViewModel = stateViewModel,
                            onBack = { navController.navigateUp() },
                            onSignIn = { navController.navigate("LogInScreen") })
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
}
