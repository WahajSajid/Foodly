package com.example.foodly

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun showSnackBar(
    hostState: SnackbarHostState,
    stateViewModel: StateViewModel,
    message: String,
) {
    CoroutineScope(Dispatchers.Main).launch {
        if (stateViewModel.showSnackBar.value) {
            hostState.showSnackbar(message)
            stateViewModel.showSnackBar.value = false
        }
    }
}