package com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.modifier.modifierLocalOf
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
import com.example.foodly.ui.theme.dimOrangeColor


@Composable
fun CancelOrderScreen(
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel(),
    context: Context = LocalContext.current,
    navController: NavController = NavController(context),
    hideBottomNavBar:() -> Unit ={}
) {

    //Hide the bottom nav bar
    hideBottomNavBar()

    //Setting the status bar color
    StatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)

    //Scroll State
    val scrollState = rememberSaveable(saver = ScrollState.Saver) { ScrollState(0) }


    //Getting the context
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(color = Color(appThemeColor1.toArgb()))
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Row(modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                //Navigate back button
                IconButton(
                    onClick = {

                        //reset the input field text
                        homeScreenStateViewModel.otherReasonFieldText.value = ""

                        //Navigate back
                        navController.popBackStack()
                    },
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    //Navigate back button Icon
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
                    //Header text for the cancel order
                    Text(
                        text = homeScreenStateViewModel.cancelOrderText.value,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }

        //Elevated Card for the content
        ElevatedCard(
            modifier = Modifier
                .padding(top = 120.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
        )
        {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(start = 35.dp, end = 35.dp, top = 35.dp, bottom = 45.dp)
            ) {
                //Heading text for the cancel order
                Text(text = homeScreenStateViewModel.cancelOrderHeadingText.value)


                //Horizontal Divider to separate the heading text and the reasons items.
                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth(), thickness = 2.dp, color = Color(
                        dimOrangeColor.toArgb()
                    )
                )
                //Storing the reasons in array with remember saveable
                val reasons = rememberSaveable {
                    arrayOf(
                        "Ordered by mistake",
                        "Found a better deal elsewhere",
                        "Delivery is taking too long",
                        "Change of mind",
                        "Items are too expensive"
                    )
                }
                //Using for loop to show reasons in the UI
                for (reason in reasons.withIndex()) {
                    //Column contains each item for the reason of cancel the order
                    Column(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth()
                    ) {
                        //Row contains reason text and the radio button
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            //Text of the reason
                            Text(text = reason.value)

                            //Radio button for the reason
                            RadioButton(
                                //Assign the selected value for each item
                                selected = when (reason.index) {
                                    0 -> homeScreenStateViewModel.isReason1Selected.value
                                    1 -> homeScreenStateViewModel.isReason2Selected.value
                                    2 -> homeScreenStateViewModel.isReason3Selected.value
                                    3 -> homeScreenStateViewModel.isReason4Selected.value
                                    else -> homeScreenStateViewModel.isReason5Selected.value

                                },
                                onClick = {
                                    //Toggle the reason selected for each item
                                    when (reason.index) {
                                        0 -> {
                                            homeScreenStateViewModel.isReason1Selected.value =
                                                !homeScreenStateViewModel.isReason1Selected.value

                                            //Calling a function set the other selected to false
                                            toggleRadioButton(0, homeScreenStateViewModel)
                                        }

                                        1 -> {
                                            homeScreenStateViewModel.isReason2Selected.value =
                                                !homeScreenStateViewModel.isReason2Selected.value

                                            //Calling a function set the other selected to false
                                            toggleRadioButton(1, homeScreenStateViewModel)
                                        }

                                        2 -> {
                                            homeScreenStateViewModel.isReason3Selected.value =
                                                !homeScreenStateViewModel.isReason3Selected.value

                                            //Calling a function set the other selected to false
                                            toggleRadioButton(2, homeScreenStateViewModel)
                                        }

                                        3 -> {
                                            homeScreenStateViewModel.isReason4Selected.value =
                                                !homeScreenStateViewModel.isReason4Selected.value

                                            //Calling a function set the other selected to false
                                            toggleRadioButton(3, homeScreenStateViewModel)
                                        }

                                        else -> {
                                            homeScreenStateViewModel.isReason5Selected.value =
                                                !homeScreenStateViewModel.isReason5Selected.value
                                            //Calling a function set the other selected to false
                                            toggleRadioButton(4, homeScreenStateViewModel)
                                        }

                                    }
                                    //Storing the selected the reason
                                    homeScreenStateViewModel.reasonSelectedText.value = reason.value
                                    Toast.makeText(
                                        context,
                                        homeScreenStateViewModel.reasonSelectedText.value,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                colors = RadioButtonColors(
                                    selectedColor = Color(appThemeColor2.toArgb()),
                                    unselectedColor = Color(
                                        appThemeColor2.toArgb()
                                    ),
                                    disabledSelectedColor = Color(
                                        appThemeColor2.toArgb()
                                    ),
                                    disabledUnselectedColor = Color(
                                        appThemeColor2.toArgb()
                                    )
                                )
                            )
                        }
                    }
                    //Horizontal Divider below each item.
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(), thickness = 2.dp, color = Color(
                            dimOrangeColor.toArgb()
                        )
                    )

                }
                //Other Text
                Text(
                    text = homeScreenStateViewModel.othersText.value,
                    modifier = Modifier.padding(top = 30.dp)
                )
                //Box for the edit text background and the edit text for the other text
                Box(modifier = Modifier.fillMaxWidth()) {
                    //Background Image for the edit text
                    Image(
                        painter = painterResource(R.drawable.other_reason_bg),
                        contentDescription = null, modifier = Modifier.padding(top = 10.dp)
                    )
                    //Text input field for the other reason input from the user
                    TextInputField(
                        modifier = Modifier.padding(top = 5.dp),
                        value = homeScreenStateViewModel.otherReasonFieldText.value,
                        onValueChange = {
                            homeScreenStateViewModel.otherReasonFieldText.value = it
                        },
                        placeHolder = homeScreenStateViewModel.otherReasonFieldHintText.value,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = null),
                    )

                }


                //Row that enclose the submit button inside it to center it horizontally.
                Row(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    //Submit Button
                    ElevatedButton(
                        onClick = {
                            // Order Cancelled and navigate to the order cancelled screen
                            navController.navigate("Order Cancelled")
                        },
                        modifier = Modifier
                            .padding(bottom = 50.dp)
                            .width(142.dp)
                            .height(36.dp),
                        colors = ButtonColors(
                            contentColor = Color.White, containerColor = Color(
                                appThemeColor2.toArgb()
                            ), disabledContentColor = Color.White, disabledContainerColor = Color(
                                appThemeColor2.toArgb()
                            )
                        )
                    ) {
                        Text(text = homeScreenStateViewModel.submitButtonText.value)
                    }
                }

            }
        }

    }
    //handling back button click action
    BackHandler{
        homeScreenStateViewModel.otherReasonFieldText.value = ""
        navController.popBackStack()
    }
}


//Function to toggle the selected radio button
private fun toggleRadioButton(position: Int, homeScreenStateViewModel: HomeScreenStateViewModel) {
    // storing all the states in the array
    val states = arrayOf(
        homeScreenStateViewModel.isReason1Selected,
        homeScreenStateViewModel.isReason2Selected,
        homeScreenStateViewModel.isReason3Selected,
        homeScreenStateViewModel.isReason4Selected,
        homeScreenStateViewModel.isReason5Selected
    )

    //Toggle all the states to false else the position where user selected latest.
    for (state in states.withIndex()) {
        if (position == state.index) continue
        else states[state.index].value = false
    }
}


@Preview(showSystemUi = true, name = "Cancel Order Screen")
@Composable
private fun Preview() {
    CancelOrderScreen()
}