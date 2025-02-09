@file:Suppress("DEPRECATION")

package com.example.foodly

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            context = LocalContext.current
            val snackBarHostState = remember {SnackbarHostState()}
            val activity =
                context as? Activity ?: throw IllegalStateException("Activity context is required")
            //Initializing the instance of FirebaseAuth and Firebase database
            authentication = FirebaseAuth.getInstance()
            database =
                FirebaseDatabase.getInstance("https://foodly-73947-default-rtdb.asia-southeast1.firebasedatabase.app")
            // Configure Google Sign-In
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("410591763157-918qchl9qjljbh7aaddabaehfntjhh3i.apps.googleusercontent.com")
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(this, gso)
            stateViewModel = ViewModelProvider(this)[StateViewModel::class.java]
            val navController = rememberNavController()
            FoodlyTheme {
                AnimatedNavHost(
                    navController = navController,
                    startDestination = "SplashScreen",
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(
                        route = "SplashScreen",
                        enterTransition = { slideInHorizontally { it } },
                        exitTransition = { slideOutHorizontally { -it } }
                    ) {
                        SplashScreen(navController = navController)
                        LaunchedEffect(Unit) {
                            delay(2000)
                            navController.navigate("WelcomeScreen") {
                                //
                            }
                        }
                    }
                    composable(
                        route = "WelcomeScreen",
                        enterTransition = { slideInHorizontally { it } },
                        exitTransition = { slideOutHorizontally { -it } }
                    ) {
                        WelcomeScreen(
                            navController = navController,
                            onSignIn = {
                                stateViewModel.welcomeScreenButtonClicked.value = "SignIn"
                                navController.navigate("OnBoardingScreen")
                            },
                            onSignUp = {
                                stateViewModel.welcomeScreenButtonClicked.value = "SignUp"
                                navController.navigate("OnBoardingScreen")
                            },
                            stateViewModel = stateViewModel,
                        )
                    }
                    composable(
                        route = "OnBoardingScreen",
                        enterTransition = { slideInHorizontally { -it } },
                        exitTransition = { slideOutHorizontally { it } }
                    ) {
                        OnBoardingScreen(
                            onSkip = {
                                if (stateViewModel.welcomeScreenButtonClicked.value == "SignIn") navController.navigate(
                                    "LogInScreen"
                                )
                                else navController.navigate("RegisterScreen")
                            }, stateViewModel = stateViewModel, navController = navController
                        )
                    }
                    composable(
                        route = "LogInScreen",
                        enterTransition = { slideInHorizontally { -it } },
                        exitTransition = { slideOutHorizontally { it } }
                    ) {
                        LogInScreen(
                            stateViewModel = stateViewModel,
                            onSignUp = { navController.navigate("RegisterScreen") },
                            onBack = { navController.navigateUp() },
                            onGoogleSignIn = {
                                if(NetworkUtil.isNetworkAvailable(context)){
                                    HasInternetAccess.hasInternetAccess(object :InternetAccessCallBack{
                                        override fun onInternetAvailable() {
                                            stateViewModel.showDialog.value = true
                                            stateViewModel.dialogTittle.value = "Signing In..."
                                            registerUserWithGoogle()
                                        }

                                        override fun onInternetNotAvailable() {
                                            CoroutineScope(Dispatchers.Main).launch {
                                                Toast.makeText(context,"Unstable Internet",Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    })
                                } else Toast.makeText(context,"No Internet",Toast.LENGTH_SHORT).show()
                            },
                            snackBarHostState = snackBarHostState
                            )
                    }
                    composable(
                        route = "RegisterScreen",
                        enterTransition = { slideInHorizontally { -it } },
                        exitTransition = { slideOutHorizontally { it } }
                    ) {
                        RegisterScreen(
                            stateViewModel = stateViewModel,
                            onBack = { navController.navigateUp() },
                            onSignIn = { navController.navigate("LogInScreen") },
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
                }

                //Showing progress dialog when the google sign In button clicked
                if (stateViewModel.showDialog.value) {
                    ProgressIndicatorDialog(stateViewModel = stateViewModel)
                }

                BackHandler {
                    val currentDestination = navController.currentBackStackEntry?.destination?.route
                    if (currentDestination == "WelcomeScreen") {
                        (context as Activity).finish()
                    } else {
                        navController.navigateUp()
                    }
                }
            }
        }
    }

    //Function to register user using
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


    //This function will handle the exception regarding API
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account)
        } catch (e: ApiException) {
            stateViewModel.showDialog.value = false
            stateViewModel.dialogTittle.value = ""
            Toast.makeText(this,"${e.message.toString()} firebase exception", Toast.LENGTH_SHORT).show()
            Log.d("firebase_exception", "API Exception")
        }
    }

    //Function to register the user user with google
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
                    Toast.makeText(context,"Authentication Successful",Toast.LENGTH_SHORT).show()
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
