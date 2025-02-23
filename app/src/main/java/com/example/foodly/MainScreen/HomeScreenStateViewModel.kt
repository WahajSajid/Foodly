package com.example.foodly.MainScreen

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.foodly.R

class HomeScreenStateViewModel() : ViewModel() {

    private val _searchTextFieldState = mutableStateOf("")
    val searchTextFieldState = _searchTextFieldState

    private val _timeText = mutableStateOf("")
    val timeText = _timeText

    private val _timeTextSlogan = mutableStateOf("")
    val timeTextSlogan = _timeTextSlogan

    //Show Side bar States
    private val _showProfileSideBar  = mutableStateOf(false)
    val showProfileSideBar = _showProfileSideBar

    private val _showNotificationsBar = mutableStateOf(false)
    val showNotificationsBar = _showNotificationsBar

    private val _showCartSideBar = mutableStateOf(false)
    val showCartSideBar = _showCartSideBar


    private val _recommendedText = mutableStateOf("Recommended")
    val recommendedText = _recommendedText

    private val _searchText = mutableStateOf("Search")
    val searchText = _searchText

    private val _bestSellerText = mutableStateOf("Best Seller")
    val bestSellerText = _bestSellerText

    private val _viewAllText = mutableStateOf("View All")
    val viewAllText = _viewAllText

    private val _experienceNewDishText = mutableStateOf("Experience Our\n" +
            "delicious new dish")
    val experienceNewDishText = _experienceNewDishText




    private val _isCartEmpty = mutableStateOf(false)
    val isCartEmpty = _isCartEmpty

    private val _noOfCartItems = mutableIntStateOf(0)
    val noOfCartItems = _noOfCartItems

    private val _subtotalAmount = mutableIntStateOf(0)
    val subtotalAmount = _subtotalAmount

    private val _taxAndFees = mutableIntStateOf(0)
    val taxAndFees = _taxAndFees

    private val _deliveryFee = mutableIntStateOf(0)
    val deliveryFee = _deliveryFee

    private val _total = mutableIntStateOf(0)
    val total = _total


    private val _menuItems = loadMenuItemsData().toMutableStateList()
    val menuItems = _menuItems


    private val _bestSellerItems = loadBestSellerData().toMutableStateList()
    val bestSellerItem = _bestSellerItems

}