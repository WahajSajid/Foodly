package com.example.foodly

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel

class StateViewModel:ViewModel() {
    //Account Creation Screens States
    private val _email = mutableStateOf("")
    var email = _email

    private val _password = mutableStateOf("")
    var password = _password

    private val _name = mutableStateOf("")
    var name = _name

    private val _mobileNumber = mutableStateOf("")
    var mobileNumber = _mobileNumber

    private val _showPasswordLogin = mutableStateOf(false)
    var showPasswordLogin = _showPasswordLogin


    private val _showPasswordRegister = mutableStateOf(true)
    var showPasswordRegister = _showPasswordRegister

    //focus requester
    val focusRequester1 = FocusRequester()
    val focusRequester2 = FocusRequester()
    val focusRequester3 = FocusRequester()
    val focusRequester4 = FocusRequester()
}