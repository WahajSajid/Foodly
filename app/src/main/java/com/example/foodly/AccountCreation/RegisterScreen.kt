

package com.example.foodly.AccountCreation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodly.NetworkPermissions.HasInternetAccess
import com.example.foodly.NetworkPermissions.InternetAccessCallBack
import com.example.foodly.NetworkPermissions.NetworkUtil
import com.example.foodly.ProgressIndicatorDialog
import com.example.foodly.R
import com.example.foodly.StateViewModel
import com.example.foodly.StatusBarColor
import com.example.foodly.TextInputField
import com.example.foodly.TopBar
import com.example.foodly.showSnackBar
import com.example.foodly.ui.theme.FoodlyTheme
import com.example.foodly.ui.theme.appThemeColor1
import com.example.foodly.ui.theme.appThemeColor2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    stateViewModel: StateViewModel = viewModel(),
    onBack: () -> Unit = {},
    onSignIn: () -> Unit = {},
    onGoogleSignIn: () -> Unit = {},
    onFacebookSignIn: () -> Unit = {},
    navController: NavController = NavController(LocalContext.current),
    database: FirebaseDatabase = FirebaseDatabase.getInstance(),
    authentication: FirebaseAuth = FirebaseAuth.getInstance(),
    snackBarHostState:SnackbarHostState,
) {
    val context = LocalContext.current
    if(stateViewModel.authenticationSuccessful.value){
        navController.popBackStack()
    }
    StatusBarColor(color = Color(appThemeColor1.toArgb()), darkIcons = true)
    Scaffold(snackbarHost = {
        FoodlyTheme {
            SnackbarHost(hostState = snackBarHostState){ data ->
                Snackbar(contentColor = if(stateViewModel.isOperationSuccessful.value) Color.Green else Color.Red, snackbarData = data)
            }
        }
    }, content = {paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TopBar(heading = "New Account", onBack = onBack)
            ElevatedCard(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp)
            ) {
                Column {
                    Text(
                        "Full Name",
                        style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
                        modifier = Modifier.padding(start = 55.dp, top = 10.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextInputField(
                            value = stateViewModel.name.value,
                            onValueChange = { stateViewModel.name.value = it },
                            placeHolder = "Name",
                            modifier = Modifier
                                .clickable { }
                                .padding(top = 5.dp)
                                .focusRequester(stateViewModel.focusRequester1)
                                .border(
                                    width = 1.dp,
                                    color = Color(appThemeColor1.toArgb()),
                                    shape = RoundedCornerShape(26.dp)
                                ),
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(onNext = { stateViewModel.focusRequester2.requestFocus() })
                        )
                    }
                    Text(
                        "Email",
                        style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
                        modifier = Modifier.padding(start = 55.dp, top = 10.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextInputField(
                            value = stateViewModel.email.value,
                            onValueChange = { stateViewModel.email.value = it },
                            placeHolder = "Email",
                            modifier = Modifier
                                .clickable { }
                                .padding(top = 5.dp)
                                .focusRequester(stateViewModel.focusRequester2)
                                .border(
                                    width = 1.dp,
                                    color = Color(appThemeColor1.toArgb()),
                                    shape = RoundedCornerShape(26.dp)
                                ),
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(onNext = { stateViewModel.focusRequester3.requestFocus() })
                        )
                    }

                    Text(
                        "Password",
                        style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
                        modifier = Modifier.padding(start = 55.dp, top = 10.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextInputField(
                            value = stateViewModel.password.value,
                            onValueChange = { stateViewModel.password.value = it },
                            placeHolder = "Password",
                            modifier = Modifier
                                .clickable { }
                                .padding(top = 5.dp)
                                .focusRequester(stateViewModel.focusRequester3)
                                .border(
                                    width = 1.dp,
                                    color = Color(appThemeColor1.toArgb()),
                                    shape = RoundedCornerShape(26.dp)
                                ),
                            trailingIcon = {
                                Image(
                                    if (stateViewModel.showPasswordRegister.value) painterResource(
                                        R.drawable.unhide
                                    ) else painterResource(R.drawable.hide),
                                    contentDescription = null,
                                    Modifier
                                        .size(20.dp)
                                        .clickable {
                                            stateViewModel.showPasswordRegister.value =
                                                !stateViewModel.showPasswordRegister.value
                                        }
                                )
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = null),
                            visualTransformation = if (stateViewModel.showPasswordRegister.value) VisualTransformation.None else PasswordVisualTransformation()
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "By continuing, you agree to")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Terms of Use ", color = Color(
                                    appThemeColor2.toArgb()
                                ), modifier = Modifier.clickable { }
                            )
                            Text(text = "and")
                            Text(
                                text = " Privacy Policy",
                                color = Color(appThemeColor2.toArgb()),
                                modifier = Modifier.clickable { })
                        }
                        if (stateViewModel.showDialog.value) ProgressIndicatorDialog(stateViewModel = stateViewModel)
                        ElevatedButton(
                            onClick = {
                                if (inputFieldsEmpty(stateViewModel = stateViewModel)) {
                                    stateViewModel.showSnackBar.value = true
                                    showSnackBar(hostState = snackBarHostState,stateViewModel = stateViewModel, message = "Input all fields first")
                                } else {
                                    if (stateViewModel.email.value.contains("@gmail.com")) {
                                        if (NetworkUtil.isNetworkAvailable(context)) {
                                            //Showing the progress dialog when the button clicked and the internet is available
                                            stateViewModel.showDialog.value = true
                                            stateViewModel.dialogTittle.value = "Creating Account..."
                                            HasInternetAccess.hasInternetAccess(object :
                                                InternetAccessCallBack {
                                                override fun onInternetAvailable() {
                                                    CoroutineScope(Dispatchers.Main).launch {
                                                        registerUserWithEmailAndPassword(
                                                            stateViewModel,
                                                            database,
                                                            authentication,
                                                            navController,
                                                            snackBarHostState
                                                        )
                                                    }
                                                }

                                                override fun onInternetNotAvailable() {
                                                    stateViewModel.showDialog.value = false
                                                    stateViewModel.dialogTittle.value = ""
                                                    stateViewModel.showSnackBar.value = true
                                                    stateViewModel.isOperationSuccessful.value =
                                                        false
                                                    showSnackBar(
                                                        hostState = snackBarHostState,
                                                        stateViewModel = stateViewModel,
                                                        "Unstable Internet"
                                                    )

                                                }

                                            })
                                        } else {
                                            stateViewModel.showSnackBar.value = true
                                            stateViewModel.isOperationSuccessful.value = false
                                            showSnackBar(hostState = snackBarHostState,stateViewModel = stateViewModel, message = "No Internet")
                                        }
                                    } else {
                                        stateViewModel.showSnackBar.value = true
                                        stateViewModel.isOperationSuccessful.value = false
                                        showSnackBar(hostState = snackBarHostState,stateViewModel = stateViewModel, message = "Invalid Email")
                                    }
                                }
                            },
                            modifier = Modifier
                                .width(200.dp)
                                .padding(top = 10.dp),
                            colors = ButtonDefaults.elevatedButtonColors(
                                appThemeColor2
                            )
                        ) {
                            Text(text = "Sign Up", color = Color.White)
                        }
                        Text(text = "or sign up with", modifier = Modifier.padding(top = 5.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            //Google SignIn Button
                            IconButton(onClick = onGoogleSignIn) {
                                Image(
                                    painter = painterResource(R.drawable.icons8_google),
                                    contentDescription = null
                                )
                            }
                            //Facebook SignIn Button
                            IconButton(onClick = onFacebookSignIn) {
                                Image(
                                    painter = painterResource(R.drawable.icons8_facebook),
                                    contentDescription = null
                                )
                            }
                        }


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Already have an account? ")
                            Text(
                                text = "Log In",
                                fontWeight = FontWeight.Bold,
                                color = Color(appThemeColor2.toArgb()),
                                modifier = Modifier.clickable { onSignIn() })
                        }
                    }
                }
            }
        }
    })

}

//Function to register the user using email and password
private fun registerUserWithEmailAndPassword(
    stateViewModel: StateViewModel,
    database: FirebaseDatabase = FirebaseDatabase.getInstance(),
    authentication: FirebaseAuth,
    navController: NavController,
    snackBarHostState: SnackbarHostState
) {
    val databaseReference = database.getReference("Users")
    val userData = mapOf("name" to stateViewModel.name.value, "email" to stateViewModel.email.value)
    authentication.createUserWithEmailAndPassword(
        stateViewModel.email.value,
        stateViewModel.password.value
    )
        .addOnSuccessListener { _ ->
            val userId = authentication.currentUser?.uid
            databaseReference.child(userId.toString()).setValue(userData)
            stateViewModel.showDialog.value = false
            stateViewModel.dialogTittle.value = ""
            stateViewModel.showSnackBar.value = true
            stateViewModel.isOperationSuccessful.value = true
            showSnackBar(hostState = snackBarHostState,stateViewModel = stateViewModel, message = "Registration Successful")
            CoroutineScope(Dispatchers.Main).launch {
                navController.popBackStack()
                navController.navigate("LogInScreen")
            }
        }
        .addOnFailureListener { error ->
            stateViewModel.showDialog.value = false
            stateViewModel.dialogTittle.value = ""
            stateViewModel.showSnackBar.value = true
            stateViewModel.isOperationSuccessful.value = false
            showSnackBar(hostState = snackBarHostState,stateViewModel = stateViewModel, message = error.message.toString())
        }
}


//Function to check input fields are empty or not
private fun inputFieldsEmpty(stateViewModel: StateViewModel): Boolean {
    return (stateViewModel.name.value == "" || stateViewModel.email.value == "" || stateViewModel.password.value == "")
}
