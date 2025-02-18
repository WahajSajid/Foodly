package com.example.foodly.HomeSideBars

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodly.HomeScreenStateViewModel
import com.example.foodly.R
import com.example.foodly.ui.theme.appThemeColor2
import com.example.foodly.ui.theme.checkOutButtonColor
import com.example.foodly.ui.theme.dimOrangeColor

//Side bar for the cart items
@SuppressLint("UseOfNonLambdaOffsetOverload", "UnrememberedMutableState", "RememberReturnType")
@Composable
fun CartSideBar(
    onClose: () -> Unit = {},
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel()
) {
    // Track the sidebar's horizontal offset
    var offsetX by remember { mutableFloatStateOf(0f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .width(300.dp)
            .background(Color.Transparent)
            .offset(x = offsetX.dp)
            .pointerInput(Unit) {
                val velocityTracker = androidx.compose.ui.input.pointer.util.VelocityTracker()
                detectHorizontalDragGestures(
                    onDragStart = { /* Optional: Handle drag start */ },
                    onDragEnd = {
                        // Close the sidebar if swiped beyond a threshold
                        if (offsetX > 100) {
                            onClose()
                        } else {
                            // Reset the offset if not swiped enough
                            offsetX = 0f
                        }
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        offsetX =
                            (offsetX + dragAmount).coerceAtLeast(0f) // Prevent swiping to the left
                        velocityTracker.addPosition(
                            change.uptimeMillis,
                            Offset(change.position.x, change.position.y)
                        )
                    }
                )
            }, horizontalAlignment = Alignment.End
    ) {
        ElevatedCard(
            modifier = Modifier
                .width(300.dp)
                .fillMaxHeight(),
            colors = CardColors(
                containerColor = Color(appThemeColor2.toArgb()),
                contentColor = Color.White,
                disabledContainerColor = Color(
                    appThemeColor2.toArgb()
                ),
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(topStart = 80.dp, bottomStart = 80.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.padding(top = 60.dp)) {
                    Image(
                        painter = painterResource(R.drawable.cart_icon),
                        contentDescription = null
                    )
                    Text(
                        text = "Cart",
                        style = TextStyle(
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(start = 15.dp, top = 5.dp)
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 40.dp, start = 20.dp, end = 20.dp)
                        .fillMaxWidth(), thickness = 2.dp, color = Color(
                        dimOrangeColor.toArgb()
                    )
                )
                if (homeScreenStateViewModel.isCartEmpty.value) {
                    //Showing the empty cart section when the cart is empty
                    Text(
                        text = "Your cart is empty",
                        style = TextStyle(color = Color.White, fontSize = 18.sp),
                        modifier = Modifier.padding(top = 15.dp)
                    )

                    Column(
                        modifier = Modifier
                            .padding(bottom = 150.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {/*Add the items into cart*/ }, modifier = Modifier
                                .width(184.dp)
                                .height(184.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.add_to_cart_icon),
                                contentDescription = null
                            )
                        }
                        Text(
                            text = "Want To Add \n Something? ",
                            style = TextStyle(
                                fontSize = 25.sp,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                } else {
                    //Getting the size of the cart items
                    val cartItems = loadCartData()
                    homeScreenStateViewModel.noOfCartItems.intValue = cartItems.size
                    Text(
                        text = "You have ${homeScreenStateViewModel.noOfCartItems.intValue} items in the cart",
                        style = TextStyle(color = Color.White),
                        modifier = Modifier.padding(top = 7.dp)
                    )
                    CartLazyColumn(
                        modifier = Modifier.padding(
                            top = 25.dp,
                            start = 20.dp,
                            end = 20.dp
                        )
                    )
                    Column(
                        modifier = Modifier
                            .padding(25.dp)
                            .fillMaxWidth()
                    ) {
                        //Calculating the subtotal amount of the cart items
                        val itemPrices = mutableListOf<Int>()
                        for (item in cartItems) {
                            val itemPrice = item.price * item.quantity
                            itemPrices.add(itemPrice)
                        }

                        for (price in itemPrices) {
                            homeScreenStateViewModel.subtotalAmount.intValue += price
                        }

                        val subtotalAmount = rememberSaveable {
                            mutableStateOf("Rs. " + homeScreenStateViewModel.subtotalAmount.intValue)
                        }
                        //Dummy Data for the delivery fee and tax, Replace with the original data
                        val taxAndFees = rememberSaveable {
                            mutableStateOf("Rs. " + homeScreenStateViewModel.taxAndFees.intValue)
                        }
                        val delivery = rememberSaveable {
                            mutableStateOf("Rs. " + homeScreenStateViewModel.deliveryFee.intValue)
                        }

                        for (i in 0 until 3) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 15.dp)
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Text(
                                        text = when (i) {
                                            0 -> "Subtotal"
                                            1 -> "Tax and Fees"
                                            else -> "Delivery"
                                        },
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        text = when (i) {
                                            0 -> subtotalAmount.value
                                            1 -> taxAndFees.value
                                            else -> delivery.value
                                        },
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = 18.sp,
                                        )
                                    )
                                }
                            }
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            for (i in 0..45) {
                                HorizontalDivider(
                                    thickness = 3.dp, modifier = Modifier
                                        .padding(top = 20.dp)
                                        .padding(start = 2.dp)
                                        .width(5.dp),
                                    color = Color(dimOrangeColor.toArgb())
                                )
                            }
                        }
                        //Calculating  the total amount
                        homeScreenStateViewModel.total.intValue =
                            homeScreenStateViewModel.subtotalAmount.intValue + homeScreenStateViewModel.taxAndFees.intValue + homeScreenStateViewModel.deliveryFee.intValue
                        val totalAmount =
                            "Rs. " + rememberSaveable { homeScreenStateViewModel.total.intValue }


                        Box(
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = "Total",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    text = totalAmount,
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 18.sp,
                                    )
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .padding(top = 40.dp)
                                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                        ) {
                            ElevatedButton(
                                onClick = {/*Navigate to Checkout Screen */ },
                                colors = ButtonColors(
                                    containerColor = Color(checkOutButtonColor.toArgb()),
                                    contentColor = Color(
                                        appThemeColor2.toArgb()
                                    ),
                                    disabledContainerColor = Color(
                                        checkOutButtonColor.toArgb()
                                    ),
                                    disabledContentColor = Color(
                                        appThemeColor2.toArgb()
                                    )
                                ),
                                modifier = Modifier
                                    .height(36.dp)
                                    .width(131.dp)
                            ) {
                                Text(text = "Checkout", color = Color(appThemeColor2.toArgb()))
                            }
                        }

                    }


                }
            }
        }
    }
}


//Lazy Column for the cart item
@Composable
fun CartLazyColumn(modifier: Modifier = Modifier) {
    val carItems = rememberSaveable { loadCartData() }
    LazyColumn(modifier = modifier) {
        itemsIndexed(carItems) { index, item ->
            CartLazyItemComposable(
                itemName = item.name,
                itemPrice = item.price,
                currentDate = item.date,
                currentTime = item.time,
                itemQuantity = item.quantity,
                itemImage = item.image,
                onIncreaseItem = {
                    //Increase the item by 1
                },
                onDecreaseItem = {
                    if(item.quantity > 1) {
                        //Decrease the item by 1
                    }
                }
            )
        }
    }

}

//Cart item for the cart lazy column
@Composable
fun CartLazyItemComposable(
    itemName: String = "Strawberry Cake",
    itemPrice: Int = 150,
    currentDate: String = "29/11/24",
    currentTime: String = "9:00 PM",
    itemQuantity: Int = 1,
    itemImage: Int = R.drawable.image1,
    onIncreaseItem: () -> Unit = {},
    onDecreaseItem: () -> Unit = {},
) {

    val price = "Rs. $itemPrice"


    //Splitting the notification title into 3 words each string for the line break on every 3 words of the notification string
    val words = itemName.split(" ")
    val chunkedWords = words.chunked(1).joinToString("\n") { it.joinToString(" ") }
    Column(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .height(80.dp)
        ) {
            ElevatedCard(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Image(
                    painter = painterResource(itemImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = chunkedWords, style = TextStyle(color = Color.White, fontSize = 18.sp))
                Text(
                    text = price,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = currentDate, style = TextStyle(color = Color.White))
                Text(text = currentTime, style = TextStyle(color = Color.White))
                Row(modifier = Modifier.padding(top = 7.dp)) {
                    IconButton(
                        onClick = { onDecreaseItem() }, modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.less_icon),
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp),
                            contentDescription = null
                        )
                    }
                    Text(
                        text = itemQuantity.toString(),
                        style = TextStyle(color = Color.White, fontSize = 17.sp),
                        modifier = Modifier.padding(start = 7.dp)
                    )

                    IconButton(
                        onClick = { onIncreaseItem() }, modifier = Modifier
                            .padding(start = 7.dp)
                            .width(20.dp)
                            .height(20.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.add_more_icon),
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp),
                            contentDescription = null
                        )
                    }
                }

            }


        }

        HorizontalDivider(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 5.dp)
                .fillMaxWidth(), thickness = 2.dp, color = Color(
                dimOrangeColor.toArgb()
            )
        )

    }
}

//Function to load the data
private fun loadCartData(
): List<CartItem> {
    val cartItems = mutableListOf<CartItem>()

    //Dummy Data, Replace with the original data
    val itemNames = listOf("Strawberry Cake", "Broccoli Lasagna")
    val itemPrices = listOf(150, 250)
    val itemDates = listOf("29/11/24", "30/11/24")
    val itemsTimes = listOf("9:00 PM", "10:00 AM")
    val quantitiesOfItems = listOf(1, 1)
    val itemImages = listOf(R.drawable.image1, R.drawable.image4)

    for (data in itemsTimes.withIndex()) {
        cartItems.add(
            CartItem(
                name = itemNames[data.index],
                price = itemPrices[data.index],
                image = itemImages[data.index],
                date = itemDates[data.index],
                time = itemsTimes[data.index],
                quantity = quantitiesOfItems[data.index]
            )
        )
    }
    return cartItems
}


data class CartItem(
    val name: String,
    val price: Int,
    val image: Int,
    val date: String,
    val time: String,
    var quantity: Int
)


@Preview()
@Composable
private fun Preview() {
    CartSideBar()
}