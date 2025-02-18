package com.example.foodly.HomeSideBars

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodly.R
import com.example.foodly.ui.theme.appThemeColor2
import com.example.foodly.ui.theme.dimOrangeColor

//Side bar for the notifications
@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun NotificationsSideBar(onClose: () -> Unit = {}) {
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
                Row(modifier = Modifier.padding(top = 60.dp)) {
                    Image(
                        painter = painterResource(R.drawable.notification_icon),
                        contentDescription = null
                    )
                    Text(
                        text = "Notifications",
                        style = TextStyle(
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 40.dp, start = 20.dp, end = 20.dp)
                        .fillMaxWidth(), thickness = 2.dp, color = Color(
                        dimOrangeColor.toArgb()
                    )
                )
                NotificationLazyColumn(modifier = Modifier.padding(top = 15.dp, start = 25.dp, end = 25.dp))
            }
        }
    }
}

//Lazy Column for the Notifications
@Composable
fun NotificationLazyColumn(modifier: Modifier = Modifier) {
    val notifications = rememberSaveable { loadNotificationData() }

    LazyColumn(modifier = modifier) {
        itemsIndexed(notifications) { index, item ->
            NotificationItemComposable(
                notificationText = item.notificationText,
                notificationIcon = when (item.notificationType) {
                    "favourite promotion" -> R.drawable.favourite_promotion_notification_icon
                    "order" -> R.drawable.order_notification_icon
                    "delivery" -> R.drawable.delivery_notification_icon
                    else -> R.drawable.promotion_notification_icon
                }, onClick = { /* Navigate to the desired screen*/}
            )
        }
    }

}

//Item Composable for the notifications lazy column
@Composable
fun NotificationItemComposable(
    notificationText: String = "We have added a product you might like in you free time at home",
    notificationIcon: Int = R.drawable.promotion_notification_icon,
    onClick: () -> Unit = {}
) {
    //Splitting the notification title into 3 words each string for the line break on every 3 words of the notification string
    val words = notificationText.split(" ")
    val chunkedWords = words.chunked(4).joinToString("\n") { it.joinToString(" ") }
    Column(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(notificationIcon), contentDescription = null)
            Text(
                text = chunkedWords,
                style = TextStyle(color = Color.White),
                modifier = Modifier.padding(start = 20.dp)
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(), thickness = 2.dp, color = Color(
                dimOrangeColor.toArgb()
            )
        )

    }
}


//Function to load the notifications data
private fun loadNotificationData(): List<Notification> {
    val notifications = mutableListOf<Notification>()

    //Dummy Data, Replace with the original data
    val notificationTexts = listOf(
        "We have added a product you might like.",
        "One of your favourite is on promotion",
        "Your order has been delivered",
        "The delivery on his way"
    )
    //Notification types are for the selection of notification icon, each notification will have a specific type replace with the original data later
    val notificationTypes = listOf("promotion", "favorite item promotion", "order", "delivery")

    for (data in notificationTexts.withIndex()) {
        notifications.add(
            Notification(
                notificationText = notificationTexts[data.index],
                notificationType = notificationTypes[data.index]
            )
        )
    }
    return notifications
}

data class Notification(val notificationText: String, val notificationType: String)