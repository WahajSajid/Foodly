package com.example.foodly


sealed class BottomNavItem(val route: String, val icon: Int, val title: String) {
    object Home : BottomNavItem("home", R.drawable.home, "Home")
    object Menu : BottomNavItem("menu", R.drawable.menu, "Menu")
    object Favorites : BottomNavItem("favorites",R.drawable.favorites , "Favorites")
    object MyOrders : BottomNavItem("my_orders",R.drawable.my_orders , "Orders")
    object CustomerSupport : BottomNavItem("customer_support",R.drawable.favorites , "Support")
}
