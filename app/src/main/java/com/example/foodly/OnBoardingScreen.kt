@file:Suppress("DEPRECATION")

package com.example.foodly

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun OnBoardingScreen() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)
    Column(
        modifier = Modifier
            .padding(WindowInsets.systemBars.asPaddingValues())
            .fillMaxSize()
    ) {
        //Storing On Boarding Screen Content in the array to dynamically show to the user everytime next button clicked
        val onBoardingImages =
            rememberSaveable {
                mutableStateOf(
                    arrayOf(
                        R.drawable.home_made_food,
                        R.drawable.payment,
                        R.drawable.delivery
                    )
                )
            }
        val onBoardingIcons =
            rememberSaveable {
                mutableStateOf(
                    arrayOf(
                        R.drawable.order_icon,
                        R.drawable.card_icon,
                        R.drawable.deliver_icon
                    )
                )
            }
        val onBoardingHeadings = rememberSaveable {
            mutableStateOf(
                arrayOf(
                    "Order For Food",
                    "Easy Payment",
                    "Fast Delivery"
                )
            )
        }
        val onBoardingSlogans = rememberSaveable {
            mutableStateOf(
                arrayOf(
                    "Crave it? Tap it. Get it!",
                    "Crave it? Tap it. Get it!",
                    "Hot meals, at lightning speed!"
                )
            )
        }
        //Implementing horizontal pager
        val pagerState = rememberPagerState(pageCount = { 3 })
        HorizontalPager(state = pagerState) { page ->
            Box {
                //On Boarding Screen Image
                Image(
                    painter = painterResource(onBoardingImages.value[page]),
                    contentDescription = "On Boarding Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxSize()
                ) {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(300.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            //On Boarding Screen Icon
                            Image(
                                painter = painterResource(onBoardingIcons.value[page]),
                                contentDescription = "Icon",
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(top = 20.dp)
                            )
                            //On Boarding Screen Heading
                            Text(
                                text = onBoardingHeadings.value[page], style = TextStyle(
                                    fontSize = 25.sp, fontWeight = FontWeight.W900, color = Color(
                                        appThemeColor2.toArgb()
                                    )
                                ),
                                modifier = Modifier.padding(top = 18.dp)
                            )
                            Text(
                                text = onBoardingSlogans.value[0],
                                modifier = Modifier.padding(top = 15.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .background(color = Color(appThemeColor2.toArgb()), shape = RectangleShape)
                                    .size(10.dp)
                            )
                        }
                    }
                }
            }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    OnBoardingScreen()
}