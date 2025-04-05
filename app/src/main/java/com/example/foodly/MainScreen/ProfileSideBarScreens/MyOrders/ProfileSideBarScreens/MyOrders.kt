@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodly.MainScreen.HomeScreenStateViewModel
import com.example.foodly.R
import com.example.foodly.StatusBarColor
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2
import com.example.foodly.ui.theme.dimOrangeColor

@Composable
fun MyOrdersScreen(
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel(),
    navController: NavController,
    showBottomNavBar:() -> Unit = {}
) {
    StatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)

    //Show the bottom navigation bar
    showBottomNavBar()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(color = Color(appThemeColor1.toArgb()))
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                //Navigate back button
                IconButton(
                    onClick = {
                        //Navigate back
                        navController.popBackStack()
                    },
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = null,
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(end = 50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = homeScreenStateViewModel.myOrdersText.value,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }

        ElevatedCard(
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
        )
        {
            Column {
                // Button Texts
                val buttonTexts = rememberSaveable { listOf("Active", "Completed", "Cancelled") }
                Row(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (i in 0 until 3) {
                        ElevatedButton(
                            onClick = {
                                when (i) {
                                    0 -> {
                                        homeScreenStateViewModel.activeButtonClicked.value = true
                                        homeScreenStateViewModel.completedButtonClicked.value =
                                            false
                                        homeScreenStateViewModel.cancelledButtonClicked.value =
                                            false
                                        /* Navigate to Active Orders*/
                                    }

                                    1 -> {
                                        homeScreenStateViewModel.completedButtonClicked.value = true
                                        homeScreenStateViewModel.activeButtonClicked.value = false
                                        homeScreenStateViewModel.cancelledButtonClicked.value =
                                            false
                                        /* Navigate to Completed Orders */

                                    }

                                    else -> {
                                        homeScreenStateViewModel.cancelledButtonClicked.value = true
                                        homeScreenStateViewModel.activeButtonClicked.value = false
                                        homeScreenStateViewModel.completedButtonClicked.value =
                                            false
                                        /* Navigate to Cancelled Orders */
                                    }
                                }
                            },
                            modifier = Modifier
                                .height(41.dp)
                                .width(125.dp)
                                .padding(5.dp),
                            colors = ButtonColors(
                                containerColor = when (i) {
                                    0 -> {
                                        if (homeScreenStateViewModel.activeButtonClicked.value) {
                                            Color(
                                                appThemeColor2.toArgb()
                                            )
                                        } else Color(dimOrangeColor.toArgb())
                                    }

                                    1 -> {
                                        if (homeScreenStateViewModel.completedButtonClicked.value) {
                                            Color(
                                                appThemeColor2.toArgb()
                                            )
                                        } else Color(dimOrangeColor.toArgb())
                                    }

                                    else -> {
                                        if (homeScreenStateViewModel.cancelledButtonClicked.value) {
                                            Color(
                                                appThemeColor2.toArgb()
                                            )
                                        } else Color(dimOrangeColor.toArgb())
                                    }
                                }, contentColor = when (i) {
                                    0 -> {
                                        if (homeScreenStateViewModel.activeButtonClicked.value) {
                                            Color.White
                                        } else Color(appThemeColor2.toArgb())
                                    }

                                    1 -> {
                                        if (homeScreenStateViewModel.completedButtonClicked.value) {
                                            Color.White
                                        } else Color(appThemeColor2.toArgb())
                                    }

                                    else -> {
                                        if (homeScreenStateViewModel.cancelledButtonClicked.value) {
                                            Color.White
                                        } else Color(
                                            appThemeColor2.toArgb()
                                        )
                                    }
                                }, disabledContainerColor = when (i) {
                                    0 -> {
                                        if (homeScreenStateViewModel.activeButtonClicked.value) {
                                            Color(
                                                appThemeColor2.toArgb()
                                            )
                                        } else Color(dimOrangeColor.toArgb())
                                    }

                                    1 -> {
                                        if (homeScreenStateViewModel.completedButtonClicked.value) {
                                            Color(
                                                appThemeColor2.toArgb()
                                            )
                                        } else Color(dimOrangeColor.toArgb())
                                    }

                                    else -> {
                                        if (homeScreenStateViewModel.cancelledButtonClicked.value) {
                                            Color(
                                                appThemeColor2.toArgb()
                                            )
                                        } else Color(dimOrangeColor.toArgb())
                                    }
                                }, disabledContentColor = when (i) {
                                    0 -> {
                                        if (homeScreenStateViewModel.activeButtonClicked.value) {
                                            Color.White
                                        } else Color(appThemeColor2.toArgb())
                                    }

                                    1 -> {
                                        if (homeScreenStateViewModel.completedButtonClicked.value) {
                                            Color.White
                                        } else Color(appThemeColor2.toArgb())
                                    }

                                    else -> {
                                        if (homeScreenStateViewModel.cancelledButtonClicked.value) {
                                            Color.White
                                        } else Color(
                                            appThemeColor2.toArgb()
                                        )
                                    }
                                }
                            )
                        ) {
                            Text(
                                text = buttonTexts[i],
                                fontSize = 12.sp,
                                color = when (i) {
                                    0 -> {
                                        if (homeScreenStateViewModel.activeButtonClicked.value) Color.White
                                        else Color(appThemeColor2.toArgb())
                                    }

                                    1 -> {
                                        if (homeScreenStateViewModel.completedButtonClicked.value) Color.White
                                        else Color(appThemeColor2.toArgb())
                                    }

                                    else -> {
                                        if (homeScreenStateViewModel.cancelledButtonClicked.value) Color.White
                                        else Color(appThemeColor2.toArgb())
                                    }
                                }
                            )
                        }
                    }
                }

                //Showing the respective orders upon user selected the specific one
                if (homeScreenStateViewModel.activeButtonClicked.value) {
                    ActiveOrdersComposable(navController = navController)
                } else if (homeScreenStateViewModel.completedButtonClicked.value) {
                    CompletedOrdersComposable(navController = navController)
                } else if (homeScreenStateViewModel.cancelledButtonClicked.value) {
                    CancelledOrdersComposable()
                }
            }
        }

    }
    //Back handler
    BackHandler {
        //Show the bottom navigation composable upon navigating back to the main screen
        navController.popBackStack()
    }
}


//@Preview(showSystemUi = true, name = "MyOrdersScreenPreview")
//@Composable
//private fun Preview() {
//    MyOrdersScreen()
//}