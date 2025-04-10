package com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodly.MainScreen.HomeScreenStateViewModel
import com.example.foodly.R
import com.example.foodly.TextInputField
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2

@Composable
fun AddNewAddressScreen(
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel(),
    hideBottomNavBar: () -> Unit = {},
    navigateBack: () -> Unit = {}
) {

    //Hide the bottom nav bar
    hideBottomNavBar()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(color = Color(appThemeColor1.toArgb()))
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                // Navigate back button
                IconButton(
                    onClick = {
                        // Navigate back
                        navigateBack()
                    }, modifier = Modifier.padding(start = 20.dp)
                ) {
                    // Navigate back icon image
                    Image(
                        painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = null,
                    )
                }
                // Row to place the heading text in the center
                Row(
                    modifier = Modifier
                        .padding(end = 50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    // Top heading text
                    Text(
                        text = homeScreenStateViewModel.addNewAddressText.value,
                        style = TextStyle(
                            color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }

        // Card that contains the content of the screen
        ElevatedCard(
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
        ) {

            // Column to align the screen content
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //heading image
                Image(
                    painter = painterResource(R.drawable.home_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .size(100.dp)
                )

                var inputHeading = rememberSaveable {
                    arrayOf("Name", "Address")
                }

                //for loop to render input fields
                for (i in 0 until 2) {
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
                                    painter = when (i) {
                                        0 -> painterResource(R.drawable.input_field_bg)
                                        else -> painterResource(R.drawable.address_field_bg)
                                    },
                                    contentDescription = null,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                            //Custom Input Field
                            when (i) {
                                0 -> {
                                    TextInputField(
                                        value = homeScreenStateViewModel.addressName.value,
                                        onValueChange = {
                                            homeScreenStateViewModel.addressName.value = it
                                        },
                                        modifier = Modifier.focusRequester(homeScreenStateViewModel.focusRequester1),
                                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                                        keyboardActions = KeyboardActions(onNext = { homeScreenStateViewModel.focusRequester2.requestFocus() })
                                    )
                                }

                                1 -> {
                                    TextInputField(
                                        value = homeScreenStateViewModel.address.value,
                                        onValueChange = {
                                            homeScreenStateViewModel.address.value = it
                                        },
                                        modifier = Modifier.focusRequester(homeScreenStateViewModel.focusRequester2),
                                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                                        keyboardActions = KeyboardActions(onDone = null)
                                    )
                                }
                            }
                        }
                    }
                }
                //Apply button
                ElevatedButton(
                    onClick = {/*apply changes*/ },
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .width(116.dp),
                    colors = ButtonColors(
                        containerColor = Color(appThemeColor2.toArgb()),
                        disabledContainerColor = Color(appThemeColor2.toArgb()),
                        contentColor = Color.White,
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(text = homeScreenStateViewModel.applyButtonText.value, color = Color.White)
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    AddNewAddressScreen()
}