@file:OptIn(ExperimentalAnimationApi::class)
@file:Suppress("DEPRECATION")

package com.example.foodly.MainScreen


import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodly.BottomNavigationBarColor
import com.example.foodly.HomeSideBars.CartSideBar
import com.example.foodly.HomeSideBars.NotificationsSideBar
import com.example.foodly.HomeSideBars.ProfileSideBar
import com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens.CancelOrderScreen
import com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens.LeaveReviewScreen
import com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens.MyOrdersScreen
import com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens.OrderCancelConfirmationScreen
import com.example.foodly.StatusBarColor
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(homeScreenStateViewModel: HomeScreenStateViewModel = viewModel()) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            //Only show the bottom navigation composable when the MainScreen
            if (homeScreenStateViewModel.showBottomNavigationComposable.value) {
                BottomNavBar(navController)
            }
        }
    ) { innerPadding ->

        //Changing the color of the status bar when the profile side open
        if (homeScreenStateViewModel.showProfileSideBar.value || homeScreenStateViewModel.showNotificationsBar.value || homeScreenStateViewModel.showCartSideBar.value) {
            StatusBarColor(color = Color(appThemeColor2.toArgb()), darkIcons = true)
        } else {
            StatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)
        }
        //Setting the bottom navigation bar color based on bottom navigation composable is showing or not
        if (homeScreenStateViewModel.showBottomNavigationComposable.value) {
            BottomNavigationBarColor(color = appThemeColor2, darkIcons = false)
        } else {
            BottomNavigationBarColor(color = Color.White, darkIcons = true)
        }


        Box(modifier = Modifier.fillMaxWidth()) {
            AnimatedNavHost(
                navController = navController,
                startDestination = BottomNavItem.Home.route,
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                composable(BottomNavItem.Home.route) {
                    HomeScreen(
                        homeScreenStateViewModel = homeScreenStateViewModel,
                        profileIconClick = {
                            //Show the profile side bar
                            homeScreenStateViewModel.showProfileSideBar.value = true
                        },
                        notificationIconClick = {
                            homeScreenStateViewModel.showNotificationsBar.value = true
                        },
                        cartIconClick = {
                            homeScreenStateViewModel.showCartSideBar.value = true
                        }, navController = navController,
                        showBottomNavBar = {
                            homeScreenStateViewModel.showBottomNavigationComposable.value = true
                        }
                    )
                }
                composable(BottomNavItem.Menu.route) { /*Menu Screen */ }
                composable(BottomNavItem.Favorites.route) { /*Favorites Screen */ }
                composable(BottomNavItem.MyOrders.route) {
                    MyOrdersScreen(
                        navController = navController,
                        showBottomNavBar = {
                            homeScreenStateViewModel.showBottomNavigationComposable.value = true
                        })
                }
                composable(BottomNavItem.CustomerSupport.route) { /*Customer Support*/ }

                //Cancel Order Screen
                composable(
                    route = "Cancel Order",
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            tween(1000)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            tween(1000)
                        )
                    }
                ) {
                    CancelOrderScreen(
                        homeScreenStateViewModel = homeScreenStateViewModel,
                        navController = navController,
                        hideBottomNavBar = {
                            homeScreenStateViewModel.showBottomNavigationComposable.value = false
                        }
                    )
                }


                //Order Cancelled Screen
                composable(
                    route = "Order Cancelled",
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Up,
                            tween(1000)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Down,
                            tween(1000)
                        )
                    }
                ) {
                    OrderCancelConfirmationScreen(
                        homeScreenStateViewModel = homeScreenStateViewModel,
                        navController = navController,
                        hideBottomNavBar = {
                            homeScreenStateViewModel.showBottomNavigationComposable.value = false
                        }
                    )
                }


                //Leave a review screen
                composable(
                    route = "Leave Review",
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            tween(1000)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            tween(1000)
                        )
                    }
                ) {
                    LeaveReviewScreen(
                        homeScreenStateViewModel = homeScreenStateViewModel,
                        navController = navController,
                        hideBottomNavBar = {
                            homeScreenStateViewModel.showBottomNavigationComposable.value = false
                        }
                    )
                }


            }

        }

        val context = LocalContext.current
        //Implementing Back Button Handler
        BackHandler {
            if (homeScreenStateViewModel.showProfileSideBar.value || homeScreenStateViewModel.showCartSideBar.value || homeScreenStateViewModel.showNotificationsBar.value) {
                //Hide the profile side bar
                homeScreenStateViewModel.showProfileSideBar.value = false
                //Hide the cart side bar
                homeScreenStateViewModel.showCartSideBar.value = false
                //Hide the notification side bar
                homeScreenStateViewModel.showNotificationsBar.value = false

            } else {
                if (!navController.popBackStack()) {
                    (context as Activity).finish() // Exit the app if back stack is empty
                }
            }
        }

    }
    // Showing Profile Side bar
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
            onClose = {
                //Hide the profile side bar
                homeScreenStateViewModel.showProfileSideBar.value = false
            },
            navController,
            onNavigateToOrders = {
                //Hide the profile side bar
                homeScreenStateViewModel.showProfileSideBar.value = false

                navController.navigate("my_orders")
            }
        )
    }

    // Showing Notifications Side bar
    AnimatedVisibility(
        visible = homeScreenStateViewModel.showNotificationsBar.value,
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
        NotificationsSideBar(
            onClose = { homeScreenStateViewModel.showNotificationsBar.value = false }
        )
    }


    // Showing Cart Side bar
    AnimatedVisibility(
        visible = homeScreenStateViewModel.showCartSideBar.value,
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
        CartSideBar(
            onClose = { homeScreenStateViewModel.showCartSideBar.value = false }
        )
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
            .windowInsetsPadding(WindowInsets.navigationBars)
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

@Preview(showSystemUi = true, name = "MainScreen")
@Composable
private fun Preview() {
    MainScreen()
}



