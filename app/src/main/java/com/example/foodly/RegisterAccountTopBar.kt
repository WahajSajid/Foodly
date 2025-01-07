package com.example.foodly

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodly.ui.theme.appThemeColor1

@Composable
fun TopBar(heading:String = ""){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(appThemeColor1.toArgb())),
    ) {
        Text(
            text = heading,
            style = TextStyle(
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 30.dp)
        )
    }
}