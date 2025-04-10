package com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens

import android.content.Context
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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

@Composable
fun EditProfileScreen(
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel(),
    hideBottomNavBar: () -> Unit = {},
    navigateBack:() ->Unit = {}
) {
    //Set the status bar color
    StatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)


    //Scroll State
    val scrollState = rememberSaveable(saver = ScrollState.Saver) { ScrollState(0) }


    //hide the bottom navigation bar
    hideBottomNavBar()

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
                       navigateBack()
                    },
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    //Navigate back icon image
                    Image(
                        painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = null,
                    )
                }
                //Row to place the heading text in the center
                Row(
                    modifier = Modifier
                        .padding(end = 50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    //Top heading text
                    Text(
                        text = homeScreenStateViewModel.myProfileText.value,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
        //Card that contains the content of the screen
        ElevatedCard(
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
        )
        {
            //Column to align the screen content
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                //Row to align the profile image in the center
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    //Card to to give rounded corner shape to the image
                    Card(
                        shape = RoundedCornerShape(25.dp), modifier = Modifier
                            .width(127.dp)
                            .height(127.dp)
                    ) {
                        //Profile image
                        Image(
                            modifier = Modifier
                                .height(127.dp)
                                .width(127.dp),
                            painter = painterResource(R.drawable.demo_profile_image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                var inputHeading = rememberSaveable {
                    arrayOf("Full Name", "Email", "Phone Number")
                }

                //Loop to render input fields
                for (i in 0 until 3) {
                    //Column to align input fields
                    Column(modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 25.dp)) {
                        //Input field heading
                        Text(
                            text = inputHeading[i],
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            ),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        //Using box to give bg to input field
                        Box(modifier = Modifier.fillMaxWidth()) {

                            //row to place the image in the center
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                //Input Field bg
                                Image(
                                    painter = painterResource(R.drawable.input_field_bg),
                                    contentDescription = null,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                            //Custom Input Field
                            when (i) {
                                0 -> {
                                    TextInputField(
                                        value = homeScreenStateViewModel.fullName.value,
                                        onValueChange = {
                                            homeScreenStateViewModel.fullName.value = it
                                        },
                                        modifier = Modifier.focusRequester(homeScreenStateViewModel.focusRequester1),
                                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                                        keyboardActions = KeyboardActions(onNext = { homeScreenStateViewModel.focusRequester2.requestFocus() })
                                    )
                                }

                                1 -> {
                                    TextInputField(
                                        value = homeScreenStateViewModel.email.value,
                                        onValueChange = {
                                            homeScreenStateViewModel.email.value = it
                                        },
                                        modifier = Modifier.focusRequester(homeScreenStateViewModel.focusRequester2),
                                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                                        keyboardActions = KeyboardActions(onNext = { homeScreenStateViewModel.focusRequester3.requestFocus() })
                                    )
                                }

                                2 -> {
                                    TextInputField(
                                        value = homeScreenStateViewModel.phoneNumber.value,
                                        onValueChange = {
                                            if (it.length <= 11) {
                                                homeScreenStateViewModel.phoneNumber.value = it
                                            }
                                        },
                                        modifier = Modifier.focusRequester(homeScreenStateViewModel.focusRequester3),
                                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                                        keyboardActions = KeyboardActions(onDone = null)
                                    )
                                }
                            }
                        }
                    }
                }

                //row to place the button in the center
                Row(
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    //Update profile button
                    ElevatedButton(
                        onClick = {/*update the profile*/ }, modifier = Modifier
                            .height(36.dp)
                            .width(142.dp),
                        colors = ButtonColors(
                            containerColor = Color(appThemeColor2.toArgb()),
                            disabledContainerColor = Color(appThemeColor2.toArgb()),
                            contentColor = Color.White,
                            disabledContentColor = Color.White
                        )
                    ) {
                        Text(
                            text = homeScreenStateViewModel.updateProfileButtonText.value,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    EditProfileScreen()
}