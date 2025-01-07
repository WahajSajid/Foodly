package com.example.foodly

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TextInputField(
    stateViewModel: StateViewModel = viewModel(),
    purpose: String = "",
    leadingIcon: Painter,
    placeHolder: String = "",
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    when(purpose){"name" -> stateViewModel.name.value "email" -> stateViewModel.email.value "password" ->stateViewModel.password.value else -> null}?.let {
        TextField(
        value = it,
        onValueChange = { when(purpose) {"name" -> stateViewModel.name.value = it "email" ->stateViewModel.email.value = it "password" ->stateViewModel.password.value = it} },
        leadingIcon = { Icon(painter = leadingIcon, contentDescription = "leading icon") },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        modifier = modifier,
            visualTransformation = if (stateViewModel.showPasswordLogin.value) VisualTransformation.None else PasswordVisualTransformation(),
        placeholder = { Text(text = placeHolder) },
        keyboardOptions = if (purpose == "name" || purpose == "email") KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ) else KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = when (purpose) {
            "name" -> KeyboardActions(onNext = { stateViewModel.focusRequester2.Default.requestFocus() })
            "email" -> KeyboardActions(onNext = { stateViewModel.focusRequester3.Default.requestFocus() })
            else -> KeyboardActions(
                onDone = null
            )
        }
    )
    }
}

@Preview(name = "Input Text Field")
@Composable
private fun InputFieldPreview() {
    TextInputField(leadingIcon = painterResource(R.drawable.baseline_attach_email_24))
}