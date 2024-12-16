package com.example.foodly

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class StateViewModel:ViewModel() {
    //Splash Screen States
    private val _showSplash = mutableStateOf(true)
    var showSplash = _showSplash

}