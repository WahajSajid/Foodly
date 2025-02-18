package com.example.foodly

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

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




//    private var menuNames = listOf("Snacks", "Meal", "Vegan", "Dessert", "Drinks")
//    private var menuIcons: List<Int> = listOf(
//        R.drawable.snacks,
//        R.drawable.meal,
//        R.drawable.vegan,
//        R.drawable.dessert,
//        R.drawable.drinks
//    )
//
//    private fun setMenuItemsData() = List(5) { i -> MenuItems(menuNames[i], menuIcons[i]) }
//    data class MenuItems(var menuName: String, var menuIcon: Int)

}