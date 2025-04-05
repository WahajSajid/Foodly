package com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
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
import com.example.foodly.ui.theme.appThemeColor2
import com.example.foodly.ui.theme.dimOrangeColor

@Composable
fun ActiveOrdersComposable(homeScreenStateViewModel: HomeScreenStateViewModel = viewModel(), navController: NavController, hideBottomNavBar:() -> Unit ={}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (homeScreenStateViewModel.activeOrderData.isEmpty()) {
            EmptyListComposable(
                textPart1 = "You don't have any",
                textPart2 = "active orders at this",
                textPart3 = "time"
            )
        } else {
            ActiveOrdersLazyColumn(modifier = Modifier.padding(start = 15.dp, end = 15.dp), navController = navController)
        }
    }
}

//Composable for the order list is empty
@Composable
fun EmptyListComposable(textPart1: String = "", textPart2: String = "", textPart3: String = "") {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.transfer_document_icon),
            contentDescription = null,
            modifier = Modifier
                .height(167.dp)
                .width(140.dp)
        )
        Column(
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (i in 0 until 3) {
                Text(
                    text = when (i) {
                        0 -> textPart1
                        1 -> textPart2
                        else -> textPart3
                    },
                    style = TextStyle(
                        fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(
                            appThemeColor2.toArgb()
                        )
                    )
                )
            }
        }
    }
}


//Composable for the active order Item
@Composable
fun ActiveOrderItemComposable(
    itemImage: Int = R.drawable.image1,
    itemPrice: Int = 250,
    itemName: String = "Strawberry Cake",
    date: String = "29 Nov",
    time: String = "01:20 PM",
    itemQuantity: Int = 2,
    onCancelOrder: () -> Unit = {},
    onTrackDriver: () -> Unit = {},
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(), thickness = 2.dp, color = Color(
                dimOrangeColor.toArgb()
            )
        )
        Row(modifier = Modifier.padding(top = 18.dp)) {
            ElevatedCard(
                modifier = Modifier
                    .height(108.dp)
                    .width(71.dp),
                shape = RoundedCornerShape(22.dp)
            ) {
                Image(
                    painter = painterResource(itemImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 13.dp, top = 14.dp),
            ) {
                Text(
                    text = itemName,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Row(modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)) {
                    Text(
                        text = date,
                        style = TextStyle(fontWeight = FontWeight.Light, color = Color.Black),
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(", ", color = Color.Black)
                    Text(
                        text = time,
                        style = TextStyle(fontWeight = FontWeight.Light, color = Color.Black)
                    )
                }
                ElevatedCard(
                    onClick = {
                        onCancelOrder()
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = CardColors(
                        containerColor = Color(appThemeColor2.toArgb()),
                        contentColor = Color.White,
                        disabledContentColor = Color.White,
                        disabledContainerColor = Color(
                            appThemeColor2.toArgb()
                        )
                    ),
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .height(26.dp)
                        .width(110.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = homeScreenStateViewModel.cancelOrderButtonText.value,
                            fontSize = 10.sp
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, end = 5.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Rs. $itemPrice", style = TextStyle(
                        fontWeight = FontWeight.Bold, color = Color(
                            appThemeColor2.toArgb()
                        ), fontSize = 18.sp
                    )
                )
                var quantity = ""
                 quantity = if(itemQuantity > 1){
                    "items"
                } else{
                    "item"
                }
                Text(
                    text = "$itemQuantity $quantity",
                    style = TextStyle(fontWeight = FontWeight.Light, color = Color.Black),
                    modifier = Modifier.padding(top = 4.dp, bottom = 14.dp)
                )
                ElevatedCard(
                    onClick = {
                        onTrackDriver()
                    },
                    modifier = Modifier
                        .height(26.dp)
                        .width(100.dp)
                        .padding(top = 5.dp),
                    colors = CardColors(
                        containerColor = Color(dimOrangeColor.toArgb()),
                        contentColor = Color(appThemeColor2.toArgb()),
                        disabledContentColor = Color(appThemeColor2.toArgb()),
                        disabledContainerColor = Color(
                            dimOrangeColor.toArgb()
                        )
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = homeScreenStateViewModel.trackDriverButtonText.value,
                            fontSize = 10.sp
                        )
                    }
                }

            }
        }
    }
}

//Lazy Column for the Active Orders
@Composable
fun ActiveOrdersLazyColumn(
    modifier: Modifier = Modifier,
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel(),
    navController: NavController
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(homeScreenStateViewModel.activeOrderData) { index, item ->
            ActiveOrderItemComposable(
                itemImage = item.itemImage,
                itemPrice = item.itemPrice,
                itemName = item.itemName,
                date = item.date,
                time = item.time,
                itemQuantity = item.itemQuantity,
                onCancelOrder = {
                    //Cancel the order
                    navController.navigate("Cancel Order")
                },
                onTrackDriver = {
                    //Track the order
                }
            )
        }
    }
}


//Function to load the active orders data
fun loadActiveOrderData(): MutableList<ActiveOrder> {

    val activeOrdersData = mutableListOf<ActiveOrder>()

    //Demo Data, replace with the original data
    val itemNames = listOf("Strawberry Cake", "Chicken Burger", "Sushi Wave", "Karahi")
    val itemPrices = listOf(250, 350, 450, 100)
    val itemImages =
        listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4)
    val dateList = listOf("29 Nov", "17 Oct", "25 Dec", "5 Feb")
    val timeList = listOf("01:20 PM", "02:00 PM", "11:00 AM", "9:00 AM")
    val itemQuantityList = listOf(1, 2, 2, 1)
    for (i in itemNames.withIndex()) {
        activeOrdersData.add(
            ActiveOrder(
                itemNames[i.index],
                itemPrices[i.index],
                itemImages[i.index],
                dateList[i.index],
                timeList[i.index],
                itemQuantityList[i.index]
            )
        )
    }
    return activeOrdersData
}


//Active Order Data Class
data class ActiveOrder(
    var itemName: String,
    var itemPrice: Int,
    var itemImage: Int,
    var date: String,
    var time: String,
    var itemQuantity: Int
)


//@Preview(name = "ActiveOrdersComposable", showSystemUi = true)
//@Composable
//private fun Preview() {
//    ActiveOrdersComposable()
//}