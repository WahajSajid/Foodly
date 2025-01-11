package com.example.foodly

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

interface InternetAccessCallBack{
    fun onInternetAvailable()
    fun onInternetNotAvailable()
}

object HasInternetAccess {
    fun hasInternetAccess(callback:InternetAccessCallBack){
        val client = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10,TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .url("https://www.google.com")
            .build()
        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                callback.onInternetNotAvailable()
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onInternetAvailable()
            }

        })

    }
}