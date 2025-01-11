package com.example.foodly

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel


@Preview
@Composable
fun ProgressIndicatorDialog(
    stateViewModel: StateViewModel = viewModel(),
    onDismiss:() ->Unit = {},
    dismissOnClickOutside: Boolean = false,
    dismissOnBackPress: Boolean = false
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = dismissOnClickOutside,
            dismissOnBackPress = dismissOnBackPress
        )
    ) {
        Card(modifier = Modifier.width(150.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(5.dp)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp),
                    color = colorResource(id = R.color.app_theme_color_2),
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
                Text(
                    text = stateViewModel.dialogTittle.value, fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
        }
    }
}