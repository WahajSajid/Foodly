package com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
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
fun SelectAddressScreen(
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel(),
    hideBottomNavBar: () -> Unit = {},
    navigateBack: () -> Unit = {},
    navigateToNewAddressScreen:() -> Unit = {}
) {

    // Set the status bar color
    StatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)

    // Hide the bottom nav Bar
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
                        text = homeScreenStateViewModel.deliveryAddressText.value,
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
            Column {

                //row to align the divider
                Row(
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxWidth()
                ) {
                    // Horizontal divider composable
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        thickness = 2.dp, color = Color(dimOrangeColor.toArgb())
                    )
                }

                // Use the lazy column to display address items
                if (homeScreenStateViewModel.addressData.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .padding(top = 60.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        //Show the empty list composable if there is no any address added yet.
                        EmptyListComposable(
                            textPart1 = "You don't have any",
                            textPart2 = "saved address yet",
                            textPart3 = ""
                        )
                    }
                } else {
                    AddressLazyColumn()
                }

                //Row to align the button in the center
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
//Add new address button
                    ElevatedButton(
                        onClick = navigateToNewAddressScreen, modifier = Modifier.padding(top = 20.dp), colors = ButtonColors(
                            containerColor = Color(dimOrangeColor.toArgb()),
                            disabledContainerColor = Color(dimOrangeColor.toArgb()),
                            contentColor = Color(appThemeColor2.toArgb()),
                            disabledContentColor = Color(appThemeColor2.toArgb())
                        )
                    ) {
                        //Button Text
                        Text(
                            text = homeScreenStateViewModel.addAddressButtonText.value,
                            color = Color(appThemeColor2.toArgb())
                        )
                    }
                }

            }
        }
    }
}

// Delivery Address Item
@Composable
fun DeliveryAddressItem(
    address: String,
    addressName: String,
    isSelected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {

    // Column that contains the item content
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 16.dp) // Add padding to the Column to ensure it stays within screen bounds
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Add vertical padding to the Row
        ) {
            // Home Icon
            Image(
                painter = painterResource(R.drawable.home_icon),
                contentDescription = null,
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .weight(1f) // Use weight to make Column take available horizontal space
            ) {
                // Address Name text
                Text(
                    text = addressName, style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black
                    )
                )
                // Address Text
                Text(
                    text = address, style = TextStyle(fontSize = 16.sp, color = Color.Black)
                )
            }
            // Radio Button
            RadioButton(
                selected = isSelected, onClick = onSelected, colors = RadioButtonDefaults.colors(
                    selectedColor = Color(appThemeColor2.toArgb()),
                    unselectedColor = Color(appThemeColor2.toArgb()),
                    disabledSelectedColor = Color(appThemeColor2.toArgb()),
                    disabledUnselectedColor = Color(appThemeColor2.toArgb())
                ), modifier = Modifier.padding(top = 5.dp)
            )
        }

        // Horizontal divider composable
        Divider(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally), // Center the divider horizontally
            thickness = 2.dp, color = Color(dimOrangeColor.toArgb())
        )
    }
}

// Lazy Column Composable
@Composable
fun AddressLazyColumn(
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel(),
) {
    LazyColumn {
        itemsIndexed(homeScreenStateViewModel.addressData) { index, item ->
            DeliveryAddressItem(
                address = item.address, addressName = item.addressName,
                isSelected = item.isSelected.value,
                onSelected = {
//                     Update the selected state of the list
                    homeScreenStateViewModel.addressData.forEachIndexed { i, address ->
                        address.isSelected.value =
                            i == index // Select the current item, unselect others
                    }

                    //Get the selected address
                    homeScreenStateViewModel.selectedAddress.address = item.address
                    homeScreenStateViewModel.selectedAddress.addressName = item.addressName
                    homeScreenStateViewModel.selectedAddress.isSelected = item.isSelected
                })
        }
    }
}

// Function to load the address data
fun loadAddressData(): MutableList<AddressDetail> {
    val addressDetails = mutableListOf<AddressDetail>()

    // Replace with the original data
    val addressNamesData = arrayOf("My home", "My Office", "Parent's Home")
    val addressData = arrayOf(
        "near 14 streets area, dubai chowk gali number 13",
        "near cant area, dubai chowk gali number 11",
        "near 14 streets area, dubai chowk gali number 13"
    )
    val isSelectedData = arrayOf(false, false, false)

    for (i in addressNamesData.withIndex()) {
        addressDetails.add(
            AddressDetail(
                addressNamesData[i.index],
                addressData[i.index],
                mutableStateOf(isSelectedData[i.index])
            )
        )
    }
    return addressDetails
}

// Data class
data class AddressDetail(
    var addressName: String,
    var address: String,
    var isSelected: MutableState<Boolean>
)

// Preview
@Preview
@Composable
private fun Preview() {
    SelectAddressScreen()
}