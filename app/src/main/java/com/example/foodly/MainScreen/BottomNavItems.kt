package com.example.foodly.MainScreen

import com.example.foodly.R


sealed class BottomNavItem(val route: String, val icon: Int, val title: String) {
    data object Home : BottomNavItem("home", R.drawable.home, "Home")
    data object Menu : BottomNavItem("menu", R.drawable.menu, "Menu")
    data object Favorites : BottomNavItem("favorites", R.drawable.favorites, "Favorites")
    data object MyOrders : BottomNavItem("my_orders", R.drawable.order, "Orders")
    data object CustomerSupport : BottomNavItem("customer_support", R.drawable.favorites, "Support")
}
