package com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodly.MainScreen.HomeScreenStateViewModel
import com.example.foodly.R
import com.example.foodly.StatusBarColor
import com.example.foodly.TextInputField
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2
import com.example.foodly.ui.theme.dimOrangeColor


@SuppressLint("UnrememberedMutableState")
@Composable
fun LeaveReviewScreen(
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel(),
    navController: NavController,
    img: Painter = painterResource(R.drawable.image1),
    itemName: String = "Soft Cake",
) {

    //Scroll State
    val scrollState = rememberSaveable(saver = ScrollState.Saver) { ScrollState(0) }

    //Setting the status bar color
    StatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)

    //Root Box for the other composables
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        //Top Heading portion of the screen
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
                    //Top heading of the screen
                    Text(
                        text = homeScreenStateViewModel.leaveReviewText.value,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }

        //Elevated card which contains the main content of the screen
        ElevatedCard(
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
        ) {
            //Column to align the content vertically
            Column(
                modifier = Modifier
                    .padding(start = 33.dp, end = 33.dp)
                    .verticalScroll(scrollState)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Card that contains item image
                Card(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .width(157.dp)
                        .height(157.dp),
                    shape = RoundedCornerShape(40.dp)
                ) {
                    //Item Image
                    Image(
                        painter = img,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                //Item name
                Text(
                    text = itemName,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(top = 15.dp)
                )

                //Slogan Texts
                Text(
                    text = homeScreenStateViewModel.leaveReviewScreenText1.value,
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier.padding(top = 30.dp)
                )
                Text(
                    text = homeScreenStateViewModel.leaveReviewScreenText2.value,
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Light
                    ),
                )

                //Row that contains the stars
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    //Loop to render stars for the review
                    for (i in 0 until 5) {
                        IconButton(onClick = {
                            //Calling a function to toggle star state for each star
                            when (i) {
                                0 -> toggleReviewStars(0, homeScreenStateViewModel)
                                1 -> toggleReviewStars(1, homeScreenStateViewModel)
                                2 -> toggleReviewStars(2, homeScreenStateViewModel)
                                3 -> toggleReviewStars(3, homeScreenStateViewModel)
                                else -> toggleReviewStars(4, homeScreenStateViewModel)
                            }
                        }, modifier = Modifier.padding(3.dp)) {
                            Image(
                                //Setting the each star to filled or unfilled star drawable based on the review star selected
                                painter = when (i) {
                                    0 -> {
                                        if (homeScreenStateViewModel.star1.value) painterResource(R.drawable.filled_star)
                                        else painterResource(R.drawable.star_icon)
                                    }

                                    1 -> {
                                        if (homeScreenStateViewModel.star2.value) painterResource(R.drawable.filled_star)
                                        else painterResource(R.drawable.star_icon)
                                    }

                                    2 -> {
                                        if (homeScreenStateViewModel.star3.value) painterResource(R.drawable.filled_star)
                                        else painterResource(R.drawable.star_icon)
                                    }

                                    3 -> {
                                        if (homeScreenStateViewModel.star4.value) painterResource(R.drawable.filled_star)
                                        else painterResource(R.drawable.star_icon)
                                    }

                                    else -> {
                                        if (homeScreenStateViewModel.star5.value) painterResource(R.drawable.filled_star)
                                        else painterResource(R.drawable.star_icon)
                                    }
                                },
                                contentDescription = null
                            )
                        }
                    }
                }
                //Text Composable
                Text(
                    text = homeScreenStateViewModel.leaveUsCommentText.value,
                    fontSize = 20.sp, fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(top = 30.dp)
                )

                //Row to enclose input field content for the review text
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    //Box for the edit text background and the edit text for the review text
                    Box {
                        //Background Image for the edit text
                        Image(
                            painter = painterResource(R.drawable.other_reason_bg),
                            contentDescription = null, modifier = Modifier.padding(top = 10.dp)
                        )
                        //Input field for the review text
                        TextInputField(
                            modifier = Modifier.padding(top = 5.dp),
                            value = homeScreenStateViewModel.reviewInputText.value,
                            onValueChange = {
                                homeScreenStateViewModel.reviewInputText.value = it
                            },
                            placeHolder = homeScreenStateViewModel.writeReviewHint.value,
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = null),
                        )

                    }
                }

                //custom submit and cancel buttons
                Row(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    //Loop to render buttons
                    for (i in 0 until 2) {
                        ElevatedCard(
                            onClick = {
                                when (i) {
                                    0 -> {/* Do something on cancel */
                                    }

                                    1 -> {
                                        /*/Do something on submit */
                                    }
                                }

                            },
                            modifier = Modifier
                                .width(153.dp)
                                .height(36.dp)
                                .padding(5.dp),
                            colors = when (i) {
                                0 -> {
                                    //Colors for the cancel button
                                    CardColors(
                                        disabledContentColor = Color(appThemeColor2.toArgb()),
                                        contentColor = Color(appThemeColor2.toArgb()),
                                        disabledContainerColor = Color(dimOrangeColor.toArgb()),
                                        containerColor = Color(
                                            dimOrangeColor.toArgb()
                                        )
                                    )
                                }

                                else -> {
                                    //Colors for the submit button
                                    CardColors(
                                        disabledContentColor = Color.White,
                                        contentColor = Color.White,
                                        disabledContainerColor = Color(appThemeColor2.toArgb()),
                                        containerColor = Color(
                                            appThemeColor2.toArgb()
                                        )
                                    )
                                }
                            }
                        ) {
                            //Row to place the text in the center
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                //Cancel Button text
                                Text(
                                    text = when (i) {
                                        //Text for the cancel button
                                        0 -> homeScreenStateViewModel.cancelButtonText.value
                                        //Text for the submit button
                                        else -> homeScreenStateViewModel.submitButtonText.value
                                    }
                                )
                            }

                        }
                    }
                }
            }
        }


    }
}

//Function to change the drawable for the review stars
private fun toggleReviewStars(
    clickedStar: Int,
    homeScreenStateViewModel: HomeScreenStateViewModel
) {
    //Variable to iterate the loop
    var iterator1 = clickedStar
    var iterator2 = clickedStar + 1
    //Storing all the states of states
    val states = arrayOf(
        homeScreenStateViewModel.star1,
        homeScreenStateViewModel.star2,
        homeScreenStateViewModel.star3,
        homeScreenStateViewModel.star4,
        homeScreenStateViewModel.star5
    )
    //Loop to set the star states to true
    while (iterator1 >= 0) {
        states[iterator1].value = true
        iterator1--
    }
    //Loop to set remaining star states to false
    while (iterator2 <= 4) {
        states[iterator2].value = false
        iterator2++
    }
}


//@Preview(name = "Review Screen")
//@Composable
//private fun Preview() {
//    LeaveReviewScreen()
//}