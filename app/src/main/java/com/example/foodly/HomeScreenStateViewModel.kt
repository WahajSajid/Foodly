package com.example.foodly

import androidx.compose.runtime.mutableFloatStateOf
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

    private val _showProfileSideBar  = mutableStateOf(false)
    val showProfileSideBar = _showProfileSideBar




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