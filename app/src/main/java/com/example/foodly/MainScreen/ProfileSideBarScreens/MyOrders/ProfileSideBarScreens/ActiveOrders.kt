package com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodly.R

@Composable
fun ActiveOrdersScreen() {

}


//Composable for the active order Item


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


@Preview(showSystemUi = true, name = "ActiveOrdersScreen")
@Composable
private fun Preview() {
    ActiveOrdersScreen()
}