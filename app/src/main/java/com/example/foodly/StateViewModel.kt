package com.example.foodly

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel

class StateViewModel:ViewModel() {
    //Login Screen States
    private val _email = mutableStateOf("")
    var email = _email

    private val _password = mutableStateOf("")
    var password = _password

    private val _showPasswordLogin = mutableStateOf(false)
    var showPasswordLogin = _showPasswordLogin

    //Register Screen
    private val _name = mutableStateOf("")
    var name = _name


    //focus requester
    val focusRequester1 = FocusRequester()
    val focusRequester2 = FocusRequester()
    val focusRequester3 = FocusRequester()
    val focusRequester4 = FocusRequester()
}