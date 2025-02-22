@file:Suppress("DEPRECATION")

package com.example.foodly

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.example.foodly.AccountCreation.LogInScreen
import com.example.foodly.AccountCreation.RegisterScreen
import com.example.foodly.MainScreen.HomeScreenStateViewModel
import com.example.foodly.MainScreen.MainScreen
import com.example.foodly.NetworkPermissions.HasInternetAccess
import com.example.foodly.NetworkPermissions.InternetAccessCallBack
import com.example.foodly.NetworkPermissions.NetworkUtil
import com.example.foodly.OnBoardingScreen.OnBoardingScreen
import com.example.foodly.OnBoardingScreen.WelcomeScreen
import com.example.foodly.SplashScreen.SplashScreen
import com.example.foodly.ui.theme.FoodlyTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    private lateinit var authentication: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var stateViewModel: StateViewModel
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var homeScreenStateViewModel: HomeScreenStateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //Setting the application on a light mode always
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            // Using shared preferences to store the user_credentials in the local storage
            sharedPreferences = getSharedPreferences("User_Credentials", MODE_PRIVATE)
            val userInstalled = sharedPreferences.getBoolean("user_installed", false)
            val userRegistered = sharedPreferences.getBoolean("user_registered", false)
            val userGoneThroughWelcomeScreen =
                sharedPreferences.getBoolean("gone_through_welcomeScreen", false)
            val userGoneThroughOnBoardingScreen =
                sharedPreferences.getBoolean("gone_through_onBoardingScreen", false)
            context = LocalContext.current
            val snackBarHostState = remember { SnackbarHostState() }
            val activity =
                context as? Activity ?: throw IllegalStateException("Activity context is required")

            // Initialize FirebaseAuth and FirebaseDatabase
            authentication = FirebaseAuth.getInstance()
            database =
                FirebaseDatabase.getInstance("https://foodly-73947-default-rtdb.asia-southeast1.firebasedatabase.app")

            // Configure Google Sign-In
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("410591763157-918qchl9qjljbh7aaddabaehfntjhh3i.apps.googleusercontent.com")
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(this, gso)

            // Initialize ViewModels
            stateViewModel = ViewModelProvider(this)[StateViewModel::class.java]
            homeScreenStateViewModel = ViewModelProvider(this)[HomeScreenStateViewModel::class.java]

            val navController = rememberNavController()

            // Handling navigation from the splash screen
            val nextScreen = when {
                userRegistered || userInstalled -> "Main Screen"
                userGoneThroughOnBoardingScreen -> "LogInScreen"
                userGoneThroughWelcomeScreen -> "OnBoardingScreen"
                else -> "WelcomeScreen"
            }

            // Use LaunchedEffect to delay the initial navigation
            LaunchedEffect(Unit) {
                delay(1000) // Delay for 1 second
                // Navigate to the next screen and clear SplashScreen properly
                navController.navigate(nextScreen) {
                    popUpTo("SplashScreen") { inclusive = true }
                }
            }

            AnimatedNavHost(
                navController = navController,
                startDestination = "SplashScreen",
                modifier = Modifier.fillMaxSize()
            ) {
                composable(
                    route = "SplashScreen",
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
                    SplashScreen(navController = navController)
                }

                composable(
                    route = "WelcomeScreen",
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
                    WelcomeScreen(
                        onSignIn = {
                            stateViewModel.welcomeScreenButtonClicked.value = "SignIn"
                            sharedPreferences.edit()
                                .putBoolean("gone_through_welcomeScreen", true).apply()
                            navController.popBackStack()
                            navController.navigate("OnBoardingScreen")
                        },
                        onSignUp = {
                            stateViewModel.welcomeScreenButtonClicked.value = "SignUp"
                            sharedPreferences.edit()
                                .putBoolean("gone_through_welcomeScreen", true).apply()
                            navController.popBackStack()
                            navController.navigate("OnBoardingScreen")
                        },
                        onNotNow = {
                            stateViewModel.welcomeScreenButtonClicked.value = "Skip Login"
                            sharedPreferences.edit()
                                .putBoolean("gone_through_welcomeScreen", true).apply()
                            navController.popBackStack()
                            navController.navigate("OnBoardingScreen")
                        }
                    )
                }

                composable(
                    route = "OnBoardingScreen",
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
                    OnBoardingScreen(
                        onSkip = {
                            when (stateViewModel.welcomeScreenButtonClicked.value) {
                                "SignIn" -> {
                                    sharedPreferences.edit()
                                        .putBoolean("gone_through_onBoardingScreen", true)
                                        .apply()
                                    navController.popBackStack()
                                    navController.navigate("LogInScreen")
                                }

                                "Skip Login" -> {
                                    sharedPreferences.edit().putBoolean("user_installed", true)
                                        .apply()
                                    navController.navigate("Main Screen") {
                                        popUpTo("SplashScreen") { inclusive = true }
                                    }
                                }

                                else -> {
                                    sharedPreferences.edit()
                                        .putBoolean("gone_through_onBoardingScreen", true)
                                        .apply()
                                    navController.popBackStack()
                                    navController.navigate("RegisterScreen")
                                }
                            }
                        },
                        stateViewModel = stateViewModel,
                        navController = navController,
                        sharedPreferences = sharedPreferences
                    )
                }

                composable(
                    route = "LogInScreen",
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
                    LogInScreen(
                        stateViewModel = stateViewModel,
                        onSignUp = { navController.navigate("RegisterScreen") },
                        onBack = {
                            if (!navController.popBackStack()) {
                                (context as Activity).finish()
                            }
                        },
                        onGoogleSignIn = {
                            if (NetworkUtil.isNetworkAvailable(context)) {
                                HasInternetAccess.hasInternetAccess(object :
                                    InternetAccessCallBack {
                                    override fun onInternetAvailable() {
                                        stateViewModel.showDialog.value = true
                                        stateViewModel.dialogTittle.value = "Signing In..."
                                        registerUserWithGoogle()
                                    }

                                    override fun onInternetNotAvailable() {
                                        CoroutineScope(Dispatchers.Main).launch {
                                            Toast.makeText(
                                                context,
                                                "Unstable Internet",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                })
                            } else {
                                Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        },
                        snackBarHostState = snackBarHostState,
                        navController = navController,
                        authenticator = authentication,
                        sharedPreferences = sharedPreferences
                    )
                }

                composable(
                    route = "RegisterScreen",
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
                    RegisterScreen(
                        stateViewModel = stateViewModel,
                        onBack = { navController.popBackStack() },
                        onSignIn = {
                            navController.navigate("LogInScreen") {
                                popUpTo("RegisterScreen") { inclusive = true }
                            }
                        },
                        onGoogleSignIn = {
                            stateViewModel.dialogTittle.value = "Signing In..."
                            stateViewModel.showDialog.value = true
                            registerUserWithGoogle()
                        },
                        database = database,
                        authentication = authentication,
                        navController = navController,
                        snackBarHostState = snackBarHostState
                    )
                }

                composable(
                    route = "Main Screen",
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
                    MainScreen(homeScreenStateViewModel = homeScreenStateViewModel)
                }
            }

            // Show progress dialog when Google Sign-In button is clicked
            if (stateViewModel.showDialog.value) {
                ProgressIndicatorDialog(stateViewModel = stateViewModel)
            }

            // Navigate to MainScreen after successful login
            if (stateViewModel.authenticationSuccessful.value) {
                stateViewModel.showDialog.value = false
//                    val startDestination = when {
//                        userGoneThroughOnBoardingScreen -> "LogInScreen"
//                        userGoneThroughWelcomeScreen -> "OnBoardingScreen"
//                        else -> "WelcomeScreen"
//                    }
                navController.navigate("Main Screen") {
                    popUpTo("Splash Screen") { inclusive = true }
                }
            }

            // Handle back button press
            BackHandler {
                if (!navController.popBackStack()) {
                    (context as Activity).finish() // Exit the app if back stack is empty
                }
            }

        }
    }

    // Function to register user using Google
    private fun registerUserWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    // Handle Google Sign-In result
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account)
        } catch (e: ApiException) {
            stateViewModel.showDialog.value = false
            stateViewModel.dialogTittle.value = ""
            Toast.makeText(this, "${e.message.toString()} firebase exception", Toast.LENGTH_SHORT)
                .show()
            Log.d("firebase_exception", "API Exception")
        }
    }

    // Function to authenticate with Firebase using Google credentials
    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val databaseReference = database.getReference("Users")
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        authentication.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val userId = authentication.currentUser?.uid
                    val email = account?.email
                    val name = account?.displayName
                    val userDetails = hashMapOf("email" to email, "name" to name)
                    stateViewModel.showDialog.value = false
                    stateViewModel.dialogTittle.value = ""
                    databaseReference.child(userId.toString()).setValue(userDetails)
                    sharedPreferences.edit().putBoolean("user_registered", true).apply()
                    stateViewModel.authenticationSuccessful.value = true
                    stateViewModel.signInSuccessful.value = true
                } else {
                    stateViewModel.showDialog.value = false
                    stateViewModel.dialogTittle.value = ""
                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }
}