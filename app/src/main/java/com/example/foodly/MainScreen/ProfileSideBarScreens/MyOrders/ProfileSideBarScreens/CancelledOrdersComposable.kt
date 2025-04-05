package com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import com.example.foodly.MainScreen.HomeScreenStateViewModel
import com.example.foodly.R
import com.example.foodly.ui.theme.appThemeColor2
import com.example.foodly.ui.theme.dimOrangeColor


@Composable
fun CancelledOrdersComposable(homeScreenStateViewModel: HomeScreenStateViewModel = viewModel()) {

    //Root Column
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Showing the empty list composable when there are no any completed order
        if (homeScreenStateViewModel.cancelledOrderData.isEmpty()) {
            EmptyListComposable(
                textPart1 = "You don't have any",
                textPart2 = "cancelled orders at this",
                textPart3 = "time"
            )
        } else {
            CancelledOrdersLazyColumn(modifier = Modifier.padding(15.dp))
        }
    }

}


//Composable for the completed order Item for the lazy column
@Composable
fun CancelledOrderItemComposable(
    itemImage: Int = R.drawable.image1,
    itemPrice: Int = 250,
    itemName: String = "Strawberry Cake",
    date: String = "29 Nov",
    time: String = "01:20 PM",
    itemQuantity: Int = 2,
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel()
) {
    //Root Column for the other composables
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        //Horizontal Divider to separate each item
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(), thickness = 2.dp, color = Color(
                dimOrangeColor.toArgb()
            )
        )
        //Root row for the item composable
        Row(modifier = Modifier.padding(top = 18.dp)) {
            //Elevated card to wrap the item image
            ElevatedCard(
                modifier = Modifier
                    .height(108.dp)
                    .width(71.dp),
                shape = RoundedCornerShape(22.dp)
            ) {
                //Image for the item.
                Image(
                    painter = painterResource(itemImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            //Column that contains item name, order date and time, order delivered text, and button
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(start = 13.dp, top = 5.dp),
            ) {
                //Row that contains the Item name and Item Price text composable
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //Text for the item name
                    Text(
                        text = itemName,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )

                    //Text Composable for the item price
                    Text(
                        text = "Rs. $itemPrice", style = TextStyle(
                            fontWeight = FontWeight.Bold, color = Color(
                                appThemeColor2.toArgb()
                            ), fontSize = 18.sp
                        )
                    )
                }

                //Row that contains the "Date & time" and item quantity Composable
                Row(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    //Row that contains the date and time text
                    Row {
                        //Text composable for the date text
                        Text(
                            text = date,
                            style = TextStyle(fontWeight = FontWeight.Light, color = Color.Black),
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Text(", ", color = Color.Black)
                        //Text composable for the time text
                        Text(
                            text = time,
                            style = TextStyle(fontWeight = FontWeight.Light, color = Color.Black)
                        )
                    }

                    //Logic to show item and items text based on the item quantity
                    var quantity = ""
                    quantity = if (itemQuantity > 1) {
                        "items"
                    } else {
                        "item"
                    }

                    //Text Composable for the item quantity.
                    Text(
                        text = "$itemQuantity $quantity",
                        style = TextStyle(fontWeight = FontWeight.Light, color = Color.Black)
                    )

                }

                //Row that contains the ordered delivered icon and text
                Row(modifier = Modifier.padding(bottom = 4.dp)) {
                    //ordered delivered icon
                    Image(
                        painter = painterResource(R.drawable.cancelled_order_icon),
                        contentDescription = null
                    )

                    //ordered delivered text
                    Text(
                        text = homeScreenStateViewModel.orderCancelledText.value, color = Color(
                            appThemeColor2.toArgb(),
                        ), modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
        }
    }
}

//Lazy Column for the Active Orders
@Composable
fun CancelledOrdersLazyColumn(
    modifier: Modifier = Modifier,
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel()
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(homeScreenStateViewModel.completedOrderData) { index, item ->
            CancelledOrderItemComposable(
                itemImage = item.itemImage,
                itemPrice = item.itemPrice,
                itemName = item.itemName,
                date = item.date,
                time = item.time,
                itemQuantity = item.itemQuantity,
            )
        }
    }
}


//Function to load the active orders data
fun loadCancelledOrdersData(): MutableList<CancelledOrder> {

    val cancelledOrdersData = mutableListOf<CancelledOrder>()

    //Demo Data, replace with the original data
    val itemNames = listOf("Strawberry Cake", "Chicken Burger", "Sushi Wave", "Karahi")
    val itemPrices = listOf(250, 350, 450, 100)
    val itemImages =
        listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4)
    val dateList = listOf("29 Nov", "17 Oct", "25 Dec", "5 Feb")
    val timeList = listOf("01:20 PM", "02:00 PM", "11:00 AM", "9:00 AM")
    val itemQuantityList = listOf(1, 2, 2, 1)
    for (i in itemNames.withIndex()) {
        cancelledOrdersData.add(
            CancelledOrder(
                itemNames[i.index],
                itemPrices[i.index],
                itemImages[i.index],
                dateList[i.index],
                timeList[i.index],
                itemQuantityList[i.index]
            )
        )
    }
    return cancelledOrdersData
}


//Active Order Data Class
data class CancelledOrder(
    var itemName: String,
    var itemPrice: Int,
    var itemImage: Int,
    var date: String,
    var time: String,
    var itemQuantity: Int
)


@Preview
@Composable
private fun Preview() {
    CancelledOrderItemComposable()
}