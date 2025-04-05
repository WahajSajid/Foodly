package com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodly.MainScreen.HomeScreenStateViewModel
import com.example.foodly.R
import com.example.foodly.StatusBarColor
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2


@Composable
fun OrderCancelConfirmationScreen(
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel(),
    navController: NavController,
    hideBottomNavBar:() -> Unit = {}
) {
    
    //Hide the bottom navigation bar
    hideBottomNavBar()

    //Getting the context
    val context = LocalContext.current

    //Setting the status bar color
    StatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)

    //Box that contains all the composable for the screen (Root Composable)
    Box(
        modifier = Modifier
            .background(color = Color(appThemeColor1.toArgb()))
            .padding(WindowInsets.statusBars.asPaddingValues())
            .fillMaxSize(),
    ) {
        //Row to place navigate back button
        Row(
            modifier = Modifier
                .padding(start = 20.dp, top = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            //Navigate back button
            IconButton(
                onClick = {
                    //Navigate back
                    navController.popBackStack()
                },
            ) {
                //Navigate back button Icon
                Image(
                    painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = null,
                )
            }
        }

        //Column for the main composable
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 180.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //Order Canceled Icons
            Image(painter = painterResource(R.drawable.cancel_sequence), contentDescription = null)
            Image(
                painter = painterResource(R.drawable._order_cancelled_),
                contentDescription = null,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
            )
            //Order Cancelled successfully texts
            Text(text = homeScreenStateViewModel.orderCancelledText1.value, color = Color.Black)
            Text(text = homeScreenStateViewModel.orderCancelledText2.value, color = Color.Black)
        }

        //Column that contains the bottom text
        Column(
            modifier = Modifier
                .padding(bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            //Bottom Texts
            Text(text = homeScreenStateViewModel.bottomText1.value, color = Color.Black)
            Text(text = homeScreenStateViewModel.bottomText2.value, color = Color.Black)

        }
    }
}


//Preview Function
//@Preview(showSystemUi = true, name = "Order Cancel Confirmation Screen")
//@Composable
//private fun Preview() {
//    OrderCancelConfirmationScreen()
//}