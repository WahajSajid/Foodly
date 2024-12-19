package com.example.foodly

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class StateViewModel:ViewModel() {
    //Splash Screen States
    private val _showSplash = mutableStateOf(true)
    var showSplash = _showSplash

    var currentScreen = mutableStateOf<Screens>(Screens.SplashScreen)

    private val _showWelcomeScreen = mutableStateOf(false)
    var showWelcomeScreen = _showWelcomeScreen

    var clicked = mutableStateOf("SignUp")

    //Welcome Screen States
    private val _showOnBoardingScreen = mutableStateOf(false)
    var showOnBoardingScreen = _showOnBoardingScreen

    //OnBoarding Screen States
    private val _screenProgress = mutableStateOf(0)
    var screenProgress = _screenProgress
}