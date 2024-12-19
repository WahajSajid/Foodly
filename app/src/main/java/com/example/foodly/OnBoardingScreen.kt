@file:Suppress("DEPRECATION")

package com.example.foodly

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2
import com.example.foodly.ui.theme.dim_appThemeColor2
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    stateViewModel: StateViewModel = StateViewModel(),
    context: Context = LocalContext.current,
    navController: NavController = NavController(context)
) {
    val systemUiController = rememberSystemUiController()
    val screenProgress = rememberSaveable { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Color(appThemeColor1.toArgb()),
            darkIcons = true
        )
        systemUiController.setStatusBarColor(
            color = Color(appThemeColor1.toArgb()),
            darkIcons = true
        )
    }
    Column(
        modifier = Modifier
            .padding(WindowInsets.statusBars.asPaddingValues())
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
                    "Pay swiftly, eat happily!",
                    "Hot meals, at lightning speed!"
                )
            )
        }
        //Implementing horizontal pager
        val pagerState = rememberPagerState(pageCount = { 3 })
        Box {
            HorizontalPager(state = pagerState) { page ->
                //On Boarding Screen Image
                Column (verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start ,modifier = Modifier.fillMaxSize()){
                    Image(
                        painter = painterResource(onBoardingImages.value[page]),
                        contentDescription = "On Boarding Image",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.End, modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp, top = 15.dp)
            ) {
                //Skip Button
                Button(
                    onClick = {},
                    modifier = Modifier.border(width = 1.dp, color = Color.Transparent),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color.Transparent, contentColor = Color(
                            appThemeColor2.toArgb()
                        )
                    )
                ) {
                    Row {
                        Text("Skip")
                        Image(
                            painter = painterResource(R.drawable.baseline_arrow_forward_ios_24),
                            contentDescription = "Skip Button Icon",
                            modifier = Modifier.padding(start = 5.dp, top = 4.dp)
                        )
                    }
                }
            }
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
                            painter = if (screenProgress.value < 3) {
                                painterResource(onBoardingIcons.value[screenProgress.value])
                            } else {
                                painterResource(onBoardingIcons.value[2])
                            },
                            contentDescription = "Icon",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(top = 20.dp)
                        )
                        //On Boarding Screen Heading
                        Text(
                            text = if (screenProgress.value < 3) {
                                onBoardingHeadings.value[screenProgress.value]
                            } else {
                                onBoardingHeadings.value[2]
                            },
                            style = TextStyle(
                                fontSize = 25.sp, fontWeight = FontWeight.W900, color = Color(
                                    appThemeColor2.toArgb()
                                )
                            ),
                            modifier = Modifier.padding(top = 18.dp)
                        )
                        Text(
                            text = if (screenProgress.value < 3) {
                                onBoardingSlogans.value[screenProgress.value]
                            } else {
                                onBoardingSlogans.value[screenProgress.value]
                            },
                            modifier = Modifier.padding(top = 15.dp)
                        )
                        Row(modifier = Modifier.padding(top = 15.dp)) {
                            for (i in 0 until 3) {
                                Box(
                                    modifier = Modifier
                                        .padding(end = 3.dp)
                                        .background(
                                            color = if (i == screenProgress.value) {
                                                Color(rememberSaveable { appThemeColor2.toArgb() })
                                            } else {
                                                Color(rememberSaveable { dim_appThemeColor2.toArgb() })
                                            },
                                            shape = CircleShape
                                        )
                                        .size(10.dp)
                                )
                            }
                        }
                        val coroutineScope = rememberCoroutineScope()
                        ElevatedButton(
                            onClick = {
                                screenProgress.value++
                                val nextPage =
                                    (pagerState.currentPage + 1).coerceAtMost(pagerState.pageCount - 1)
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(nextPage)
                                }
                                if (screenProgress.value > 2) {
                                    screenProgress.value--
                                    Toast.makeText(
                                        context,
                                        "You have reached maximum",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .width(160.dp),
                            colors = ButtonDefaults.elevatedButtonColors(colorResource(R.color.app_theme_color_2))
                        ) {
                            Text("Next", color = Color.White)
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