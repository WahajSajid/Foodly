package com.example.foodly


import android.annotation.SuppressLint
import android.view.VelocityTracker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DrawerValue
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalDrawer
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2
import kotlinx.coroutines.launch

@Composable
fun MainScreen(homeScreenStateViewModel: HomeScreenStateViewModel = viewModel()) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->

        //Changing the color of the status bar when the profile side open
        if(homeScreenStateViewModel.showProfileSideBar.value){
            StatusBarColor(color = Color(appThemeColor2.toArgb()), darkIcons = true)
        } else{
            StatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(BottomNavItem.Home.route) {
                    HomeScreen(profileIconClick = {
                        homeScreenStateViewModel.showProfileSideBar.value = true
                    })
                }
                composable(BottomNavItem.Menu.route) { /*Menu Screen */ }
                composable(BottomNavItem.Favorites.route) { /*Favorites Screen */ }
                composable(BottomNavItem.MyOrders.route) { /*My Orders */ }
                composable(BottomNavItem.CustomerSupport.route) { /*Customer Support*/ }
            }

        }
    }
    // Sidebar for profile screen
    AnimatedVisibility(
        visible = homeScreenStateViewModel.showProfileSideBar.value,
        enter = slideInHorizontally(
            initialOffsetX = { it }, // Slide in from the right
            animationSpec = tween(durationMillis = 300)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it }, // Slide out to the right
            animationSpec = tween(durationMillis = 300)
        ),
        modifier = Modifier.fillMaxHeight()
    ) {
        ProfileSideBar(
            onClose = { homeScreenStateViewModel.showProfileSideBar.value = false }
        )
    }
}

//profile side bar
@SuppressLint("AutoboxingStateValueProperty", "UseOfNonLambdaOffsetOverload")
@Composable
fun ProfileSideBar(onClose: () -> Unit = {}) {

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
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //User Profile Data Section

                //Test Data, Replace with actual data
                val userImage = remember { mutableIntStateOf(R.drawable.profile_image) }
                val userName = remember { mutableStateOf("Wahaj Sajid") }
                val userId = remember { mutableStateOf("mwahajsajidali@gmail.com") }

                Row(modifier = Modifier.padding(top = 60.dp)) {
                    Card(modifier = Modifier.size(50.dp), shape = CircleShape) {
                        IconButton(
                            onClick = {/* Navigate to user profile screen*/ },
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(userImage.intValue),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Column(modifier = Modifier.padding(start = 20.dp)) {
                        Text(
                            userName.value,
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Light,
                                fontSize = 25.sp
                            )
                        )
                        Text(userId.value, style = TextStyle(color = Color.White))
                    }
                }
                //Items of Nav Bar
                ProfileNavBarLazyColumn(
                    modifier = Modifier.padding(
                        top = 40.dp,
                        start = 20.dp,
                        end = 10.dp,
                        bottom = 10.dp
                    )
                )
            }
        }
    }
}

//Item for the Lazy Column in the Profile Nav Bar
@Composable
fun ProfileNavBarItem(
    icon: Int = R.drawable.my_orders,
    itemText: String = "My Orders",
    isLogoutItem: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(
                top = if (isLogoutItem) {
                    50.dp
                } else {
                    10.dp
                }
            )
            .fillMaxWidth()
            .wrapContentSize()
    ) {
        Row(modifier = Modifier
            .clickable { onClick() }) {
            Image(painter = painterResource(icon), contentDescription = null)
            Text(
                text = itemText,
                style = TextStyle(color = Color.White, fontSize = 24.sp),
                modifier = Modifier.padding(start = 30.dp, top = 5.dp)
            )
        }
        if (!isLogoutItem) {
            Image(
                painter = painterResource(R.drawable.line_1),
                contentDescription = null,
                Modifier.padding(top = 18.dp)
            )
        }
    }
}

//Lazy Column for the profile nav bar
@Composable
fun ProfileNavBarLazyColumn(modifier: Modifier = Modifier) {
    val navBarItems = rememberSaveable { profileBarItemsDataLoad() }

    LazyColumn(modifier = modifier) {
        itemsIndexed(navBarItems) { index, item ->
            ProfileNavBarItem(item.itemIcon, item.itemText, isLogoutItem = index == 7, onClick = {
                when (index) {
                    0 -> {/* Navigate to My orders*/
                    }

                    1 -> {/* Navigate to My Profile*/
                    }

                    2 -> {/* Navigate to Delivery Address*/
                    }

                    3 -> {/* Navigate to Payment Methods*/
                    }

                    4 -> {/* Navigate to Contact Us*/
                    }

                    5 -> {/* Navigate to Help & FAQs*/
                    }

                    6 -> {/* Navigate to Settings*/
                    }

                }
            })
        }
    }
}


//Bottom Navigation Bar
@SuppressLint("SuspiciousIndentation")
@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Menu,
        BottomNavItem.Favorites,
        BottomNavItem.MyOrders,
        BottomNavItem.CustomerSupport,
    )

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)) // Rounded top corners
            .background(Color(appThemeColor2.toArgb())),
        backgroundColor = Color(appThemeColor2.toArgb())
    ) {
        val currentRoute =
            navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

//Function to load the data for Profile NavBar Items
private fun profileBarItemsDataLoad(): List<Item> {
    val items = mutableListOf<Item>()
    //Data for the lazy column
    val itemsText = listOf(
        "My Orders",
        "My Profile",
        "Delivery Address",
        "Payment Methods",
        "Contact Us",
        "Help & FAQs",
        "Settings",
        "Logout"
    )
    val itemIcons = listOf(
        R.drawable.my_orders,
        R.drawable.my_account,
        R.drawable.delivery_address,
        R.drawable.payment_method,
        R.drawable.contact_us,
        R.drawable.help_and_faq,
        R.drawable.settings,
        R.drawable.logout
    )
    for (i in 0 until 8) {
        items.add(Item(itemText = itemsText[i], itemIcon = itemIcons[i]))
    }
    return items
}


data class Item(val itemText: String, val itemIcon: Int)

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    MainScreen()
}



