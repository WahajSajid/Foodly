package com.example.foodly

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class StateViewModel:ViewModel() {
    //Splash Screen States
    private val _showSplash = mutableStateOf(true)
    var showSplash = _showSplash

    private val _showWelcomeScreen = mutableStateOf(false)
    var showWelcomeScreen = _showWelcomeScreen

    //Welcome Screen States
    private val _showOnBoardingScreen = mutableStateOf(false)
    var showOnBoardingScreen = _showOnBoardingScreen
}