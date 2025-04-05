package com.example.foodly.MainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodly.R
import com.example.foodly.StatusBarColor
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2
import com.example.foodly.ui.theme.dim_appThemeColor2
import kotlinx.coroutines.delay
import java.util.Calendar


@Composable
fun HomeScreen(
    homeScreenStateViewModel: HomeScreenStateViewModel = viewModel(),
    profileIconClick: () -> Unit = {},
    notificationIconClick: () -> Unit = {},
    cartIconClick: () -> Unit = {},
    navController: NavController,
    showBottomNavBar: () -> Unit= {}
) {
    //Show the bottom navigation bar
    showBottomNavBar()


    val scrollState = rememberSaveable(saver = ScrollState.Saver) { ScrollState(0) }
    if (homeScreenStateViewModel.showProfileSideBar.value) {
        StatusBarColor(
            color = Color(appThemeColor2.toArgb()),
            darkIcons = true
        )
    } else {
        StatusBarColor(
            color = Color(appThemeColor1.toArgb()),
            darkIcons = true
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        Column(
            modifier = Modifier
                .background(Color(appThemeColor1.toArgb()))
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center

            ) {
                // TextField on top of the Image
                TextField(
                    modifier = Modifier
                        .width(200.dp),
                    shape = RoundedCornerShape(26.dp),
                    maxLines = 1,
                    value = homeScreenStateViewModel.searchTextFieldState.value,
                    onValueChange = {
                        homeScreenStateViewModel.searchTextFieldState.value = it
                    },
                    placeholder = {
                        Text(
                            text = homeScreenStateViewModel.searchText.value,
                            color = Color.Gray
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White, // Ensure transparency
                        focusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black // Ensures cursor is visible
                    ),
                    textStyle = TextStyle(color = Color.Black), // Ensure text is visible
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                //Navigate to filter screen
                            }
                        ) {
                            Image(
                                painter = painterResource(rememberSaveable { R.drawable.filter_icon }),
                                contentDescription = "Filter Icon",
                                modifier = Modifier
                                    .size(40.dp)
                            )
                        }
                    }
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 3.dp, top = 5.dp)
                ) {
                    for (i in 0..2) {
                        IconButton(
                            onClick = {
                                when (i) {
                                    0 -> {
                                        cartIconClick()
                                    }

                                    1 -> {
                                        notificationIconClick()
                                    }

                                    else -> {
                                        profileIconClick()
                                    }
                                }
                            }
                        ) {

                            //Side bar icons
                            val sideBarIcons = rememberSaveable {
                                listOf(
                                    R.drawable.cart,
                                    R.drawable.notifications,
                                    R.drawable.accounts
                                )
                            }
                            Image(
                                painter = painterResource(sideBarIcons[i]),
                                contentDescription = null,
                                Modifier.size(45.dp)
                            )
                        }
                    }
                }

            }
            //Logic to change the text to time of day
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)

            when (hour) {
                in 5..11 -> {
                    homeScreenStateViewModel.timeText.value = "Good Morning"
                    homeScreenStateViewModel.timeTextSlogan.value = "Time for breakfast!"
                }

                12 -> {
                    homeScreenStateViewModel.timeText.value = "Good Noon"
                    homeScreenStateViewModel.timeTextSlogan.value = "Time for lunch!"
                }

                in 13..16 -> {
                    homeScreenStateViewModel.timeText.value = "Good Afternoon"
                    homeScreenStateViewModel.timeTextSlogan.value = "Light meal or snacks!"
                }

                in 17..20 -> {
                    homeScreenStateViewModel.timeText.value = "Good Evening"
                    homeScreenStateViewModel.timeTextSlogan.value = "Time for dinner!"
                }

                else -> {
                    homeScreenStateViewModel.timeText.value = "Good Night"
                    homeScreenStateViewModel.timeTextSlogan.value = "Late night snacks or rest!"
                }
            }

            Column(modifier = Modifier.padding(start = 25.dp, top = 5.dp)) {
                Text(
                    text = homeScreenStateViewModel.timeText.value,
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Serif,
                        color = Color.White
                    )
                )
                Text(
                    text = homeScreenStateViewModel.timeTextSlogan.value,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color(appThemeColor2.toArgb())
                    )
                )
            }
        }
        ElevatedCard(
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            elevation = CardDefaults.elevatedCardElevation()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .wrapContentSize()
            ) {
                MenuItemRow(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    homeScreenStateViewModel = homeScreenStateViewModel
                )

                Row(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(rememberSaveable { R.drawable.line_1 }),
                        contentDescription = null
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 15.dp, start = 20.dp, end = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = homeScreenStateViewModel.bestSellerText.value,
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                    )


                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .clickable {
                                //Navigate to Best Sellers Screen
                            }
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = homeScreenStateViewModel.viewAllText.value,
                            style = TextStyle(color = Color(appThemeColor2.toArgb())),
                            modifier = Modifier.padding(top = 2.dp)
                        )
                        Image(
                            painter = painterResource(rememberSaveable { R.drawable.baseline_arrow_forward_ios_24 }),
                            contentDescription = null,
                            modifier = Modifier.padding(start = 5.dp, top = 4.dp)
                        )
                    }
                }
                BestSellerItemsComposable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 15.dp, end = 15.dp),
                    homeScreenStateViewModel = homeScreenStateViewModel
                )

                //Temporary data for advertising banner, replace with the actual data
                val advertisingImages = rememberSaveable {
                    listOf(
                        R.drawable.image1,
                        R.drawable.image2,
                        R.drawable.image3,
                        R.drawable.image4,
                        R.drawable.image5
                    )
                }
                val advertisingText = rememberSaveable {
                    listOf(
                        "5% OFF",
                        "10% OFF",
                        "15% OFF",
                        "20% OFF",
                        "25% OFF"
                    )
                }
                //Implementing a Horizontal Pager
                val pagerState = rememberPagerState(pageCount = { 5 })
                HorizontalPager(
                    state = pagerState, modifier = Modifier
                        .padding(top = 25.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                ) { page ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(128.dp),
                            shape = RoundedCornerShape(25.dp),
                            colors = CardColors(
                                containerColor = Color(appThemeColor2.toArgb()),
                                contentColor = Color.White,
                                disabledContainerColor = Color(
                                    appThemeColor2.toArgb()
                                ),
                                disabledContentColor = Color.White
                            )
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {

                                Column(
                                    horizontalAlignment = Alignment.End,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Image(
                                        painter = painterResource(advertisingImages[page]),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .width(180.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .width(170.dp)
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.Top,
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Image(
                                        painter = painterResource(rememberSaveable { R.drawable.top_right_circle }),
                                        contentDescription = null
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .width(170.dp)
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.Bottom,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Image(
                                        painter = painterResource(rememberSaveable { R.drawable.bottom_left_circle }),
                                        contentDescription = null
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .width(170.dp)
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = homeScreenStateViewModel.experienceNewDishText.value)
                                    Text(
                                        text = advertisingText[page],
                                        style = TextStyle(
                                            fontSize = 30.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                }
                            }
                        }

                    }
                }
                //Indicators for the pager
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (i in 0 until 5) {
                        Box(
                            modifier = Modifier
                                .height(6.dp)
                                .padding(end = 3.dp)
                                .width(20.dp)
                                .background(
                                    color = if (i == pagerState.currentPage) {
                                        Color(rememberSaveable { appThemeColor2.toArgb() })
                                    } else {
                                        Color(rememberSaveable { dim_appThemeColor2.toArgb() })
                                    },
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .size(10.dp)
                        )
                    }
                }

                // Auto-scroll logic for horizontal pager
                LaunchedEffect(Unit) {
                    while (true) {
                        delay(5000) // Delay for 5 seconds
                        val nextPage =
                            (pagerState.currentPage + 1) % 5 // Loop back to 0 after the last page
                        pagerState.animateScrollToPage(nextPage)
                    }
                }

                Text(
                    modifier = Modifier.padding(top = 15.dp, start = 20.dp),
                    text = homeScreenStateViewModel.recommendedText.value,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                )

                //Demo data, replace with the original data
                val recommendedItemsImages =
                    rememberSaveable { listOf(R.drawable.burger, R.drawable.roll) }
                val ratings = rememberSaveable { listOf("5.0", "4.5") }
                val prices = rememberSaveable { listOf("150", "300") }
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                ) {
                    for (i in 0 until 2) {
                        ElevatedCard(
                            modifier = Modifier
                                .clickable {
                                    when (i) {
                                        0 -> {/*Navigate to the order*/
                                        }

                                        1 -> {/*Navigate to the order*/
                                        }
                                    }
                                }
                                .padding(start = 8.dp)
                                .width(159.dp)
                                .height(140.dp),
                            shape = RoundedCornerShape(25.dp),
                            elevation = CardDefaults.elevatedCardElevation()
                        ) {
                            Box {
                                Image(
                                    painter = painterResource(recommendedItemsImages[i]),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )

                                Row(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .padding(start = 15.dp, top = 8.dp)
                                            .width(34.dp)
                                            .height(14.dp),
                                        colors = CardColors(
                                            containerColor = Color.White,
                                            contentColor = Color.White,
                                            disabledContentColor = Color.White,
                                            disabledContainerColor = Color.White
                                        )
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxSize(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = ratings[i],
                                                color = Color.Black,
                                                fontSize = 12.sp
                                            )
                                            Text("⭐", fontSize = 10.sp)
                                        }
                                    }
                                }

                                Column(
                                    verticalArrangement = Arrangement.Bottom,
                                    horizontalAlignment = Alignment.End,
                                    modifier = Modifier
                                        .padding(bottom = 10.dp)
                                        .fillMaxSize()
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .height(16.dp)
                                            .width(38.dp),
                                        colors = CardColors(
                                            containerColor = Color(appThemeColor2.toArgb()),
                                            contentColor = Color.White,
                                            disabledContainerColor = Color.White,
                                            disabledContentColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(
                                            topStart = 10.dp,
                                            bottomStart = 10.dp
                                        )
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.End,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = "RS. " + prices[i],
                                                style = TextStyle(
                                                    fontSize = 8.sp,
                                                    fontWeight = FontWeight.Light
                                                ),
                                                modifier = Modifier.padding(end = 5.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

    }

}

/* Below are the Lazy Row and Column used in the screens */

//Lazy Row for the menu
@Composable
fun MenuItemRow(
    modifier: Modifier = Modifier,
    homeScreenStateViewModel: HomeScreenStateViewModel
) {
    LazyRow(modifier = modifier) {
        itemsIndexed(homeScreenStateViewModel.menuItems) { index, menu ->
            MenuItemComposable(menuName = menu.menuName, menuIcon = menu.menuIcon, onClick = {
                when (index) {
                    0 -> { /* Navigate to Snacks */
                    }

                    1 -> { /* Navigate to Meal */
                    }

                    2 -> { /* Navigate to Vegan */
                    }

                    3 -> { /* Navigate to Desserts */
                    }

                    else -> { /* Navigate to Drinks */
                    }
                }
            })
        }
    }
}


//Lazy Column for the Best Seller items
@Composable
fun BestSellerItemsComposable(
    modifier: Modifier = Modifier,
    homeScreenStateViewModel: HomeScreenStateViewModel
) {
    LazyRow(modifier = modifier) {
        itemsIndexed(homeScreenStateViewModel.bestSellerItem) { index, item ->
            BestSellerItemComposable(
                itemImage = item.itemImage, price = item.itemPrice,
                onClick = {
                    //Navigate to order
                })
        }
    }
}


/* Below are the item composable used in the Lazy Row and Column in the screen */

//Menu item composable
@Composable
fun MenuItemComposable(menuName: String, menuIcon: Int, onClick: () -> Unit = {}) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(70.dp)
        ) {
            Image(
                painter = painterResource(id = menuIcon),
                contentDescription = menuName, // Add a meaningful description
                modifier = Modifier.padding(2.dp)
            )
        }
        Text(
            text = menuName,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
    }
}


//Best Seller Composable for the best seller items
@Composable
fun BestSellerItemComposable(
    itemImage: Int = R.drawable.image1,
    price: Int = 500,
    onClick: () -> Unit = {}
) {
    val itemPrice = "RS. $price"
    ElevatedCard(
        modifier = Modifier
            .clickable { onClick() }
            .padding(start = 10.dp, end = 10.dp)
            .width(71.68.dp)
            .height(108.dp),
        shape = RoundedCornerShape(25.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Box {
            Image(
                painter = painterResource(itemImage), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .height(16.dp)
                        .width(38.dp),
                    colors = CardColors(
                        containerColor = Color(appThemeColor2.toArgb()),
                        contentColor = Color.White,
                        disabledContainerColor = Color.White,
                        disabledContentColor = Color.White
                    ),
                    shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = itemPrice,
                            style = TextStyle(fontSize = 8.sp, fontWeight = FontWeight.Light),
                            modifier = Modifier.padding(end = 5.dp)
                        )
                    }
                }
            }
        }
    }
}


/* Below are the function that will load the data */

//Function to load data
fun loadMenuItemsData(): List<MenuItem> {
    val menuItems = mutableListOf<MenuItem>()
    //Dummy data replace with the original data
    val menuNames = listOf("Snacks", "Meal", "Vegan", "Dessert", "Drinks")
    val menuIcons =
        listOf(
            R.drawable.snacks,
            R.drawable.meal,
            R.drawable.vegan,
            R.drawable.dessert,
            R.drawable.drinks
        )
    for (data in menuNames.withIndex()) {
        menuItems.add(MenuItem(menuName = menuNames[data.index], menuIcon = menuIcons[data.index]))
    }
    return menuItems
}

//Function that will load the data of best seller items
fun loadBestSellerData(): MutableList<BestSellerItem> {
    val bestSellerItems = mutableListOf<BestSellerItem>()
    //List of images, replace with the original data
    val itemImages =
        listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4
        )
    //List of item prices, replace with original data
    val itemPrices = listOf(400, 500, 900, 1200)
    for (i in 0..3) {
        bestSellerItems.add(BestSellerItem(itemImage = itemImages[i], itemPrice = itemPrices[i]))
    }
    return bestSellerItems
}


//Data classes
data class MenuItem(var menuName: String, var menuIcon: Int)
data class BestSellerItem(var itemImage: Int, var itemPrice: Int)

//Previews
//@Preview(name = "Best Seller Item")
//@Composable
//private fun BestSellerItemPreview() {
//    BestSellerItemComposable()
//}


//@Preview(showSystemUi = true, name = "HomeScreen")
//@Composable
//private fun Preview() {
//    HomeScreen()
//}