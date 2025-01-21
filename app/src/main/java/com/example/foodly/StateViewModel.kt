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


    private val _showPasswordLogin = mutableStateOf(false)
    var showPasswordLogin = _showPasswordLogin


    private val _showPasswordRegister = mutableStateOf(true)
    var showPasswordRegister = _showPasswordRegister

    private val _welcomeScreenButtonClicked = mutableStateOf("")
    var welcomeScreenButtonClicked = _welcomeScreenButtonClicked

    private val _showDialog = mutableStateOf(false)
    var showDialog = _showDialog

    private val _dialogTittle = mutableStateOf("")
    var dialogTittle = _dialogTittle

    private val _showSnackBar = mutableStateOf(false)
    var showSnackBar = _showSnackBar

    private val _isOperationSuccessful = mutableStateOf(false)
    var isOperationSuccessful = _isOperationSuccessful

    //focus requester
    val focusRequester1 = FocusRequester()
    val focusRequester2 = FocusRequester()
    val focusRequester3 = FocusRequester()
}