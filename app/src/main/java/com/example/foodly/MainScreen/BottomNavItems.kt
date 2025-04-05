package com.example.foodly.MainScreen

import com.example.foodly.R


sealed class BottomNavItem(val route: String, val icon: Int, val title: String) {
    data object Home : BottomNavItem("home", R.drawable.home, "")
    data object Menu : BottomNavItem("menu", R.drawable.menu, "")
    data object Favorites : BottomNavItem("favorites", R.drawable.favorites, "")
    data object MyOrders : BottomNavItem("my_orders", R.drawable.order, "")
    data object CustomerSupport : BottomNavItem("customer_support", R.drawable.favorites, "")
}
