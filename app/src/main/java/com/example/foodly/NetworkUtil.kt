@file:Suppress("DEPRECATION")

package com.example.foodly

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo!= null && activeNetworkInfo.isConnected
    }
}