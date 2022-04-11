package icu.bughub.app.composebasic.components


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import icu.bughub.app.composebasic.components.ListItem


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToDismissSample() {

    val items = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    LazyColumn() {
        items(items) { item ->

            var unread by remember {
                mutableStateOf(false)
            }

            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToEnd) unread = !unread
                    it != DismissValue.DismissedToEnd
                }
            )

            SwipeToDismiss(state = dismissState, modifier = Modifier.padding(vertical = 4.dp),
                directions = setOf( DismissDirection.EndToStart),
                 background = {
                    val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> Color.LightGray
                            DismissValue.DismissedToEnd -> Color.Green
                            DismissValue.DismissedToStart -> Color.Red
                        }
                    )
                    val alignment = when (direction) {
                        DismissDirection.StartToEnd -> Alignment.CenterStart
                        DismissDirection.EndToStart -> Alignment.CenterEnd
                    }
                    val icon = when (direction) {
                        DismissDirection.StartToEnd -> Icons.Default.Done
                        DismissDirection.EndToStart -> Icons.Default.Delete
                    }
                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                    )
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(horizontal = 20.dp),
                        contentAlignment = alignment
                    ) {
                        Icon(
                            icon,
                            contentDescription = "Localized description",
                            modifier = Modifier.scale(scale)
                        )
                    }
                }) {
                Card(
                    elevation = animateDpAsState(
                        if (dismissState.dismissDirection != null) 4.dp else 0.dp
                    ).value
                ) {
                    ListItem(
                        text = {
                            Text(
                                item.toString(),
                                fontWeight = if (unread) FontWeight.Bold else null
                            )
                        },
                        secondaryText = { Text("Swipe me left or right!") }
                    )
                }
            }

        }
    }

}

@androidx.compose.runtime.Composable
fun SwipeSample() {


}

@Preview
@Composable
fun SwipeToDismissSamplePreview() {
    SwipeSample()
}

