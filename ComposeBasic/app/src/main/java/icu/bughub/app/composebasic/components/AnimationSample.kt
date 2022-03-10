package icu.bughub.app.composebasic.components


import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import icu.bughub.app.composebasic.R


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationSample() {

    var visible by remember {
        mutableStateOf(false)
    }

    Column {
        Button(onClick = {
            visible = !visible
        }) {
            Text("点击")
        }
        AnimatedVisibility(visible = visible, enter = slideInVertically { -40 } + expandIn()) {
            Image(painter = painterResource(id = R.drawable.newbanner4), contentDescription = null)
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationSample1() {

    var visible by remember {
        mutableStateOf(false)
    }

    Column {
        Button(onClick = {
            visible = !visible
        }) {
            Text("点击")
        }

        AnimatedVisibility(visible = visible, enter = EnterTransition.None) {
            Box(
                Modifier
                    .size(300.dp)
                    .background(Color.Red)
            ) {

                Box(
                    modifier = Modifier
                        .animateEnterExit(
                            enter = slideInVertically(animationSpec = tween(1000)),
                            exit = slideOutVertically()
                        )
                        .size(150.dp)
                        .background(Color.Green)
                        .align(Alignment.Center)
                )

            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationSample2() {

    var count by remember {
        mutableStateOf(0)
    }

    Row {
        Button(onClick = {
            count++
        }) {
            Text("Add")
        }

        AnimatedContent(targetState = count) { targetCount ->
            Text("Count:$targetCount")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationSample3() {
    var count by remember {
        mutableStateOf(0)
    }

    Row {
        Button(onClick = {
            count++
        }) {
            Text("+")
        }

        Button(onClick = {
            count--
        }) {
            Text("-")
        }

        AnimatedContent(targetState = count, transitionSpec = {
            if (targetState > initialState) {
                slideInVertically { fullHeight -> fullHeight } + fadeIn() with
                        slideOutVertically { fullHeight -> -fullHeight } + fadeOut()
            } else {
                slideInVertically { fullHeight -> -fullHeight } + fadeIn() with
                        slideOutVertically { fullHeight -> fullHeight } + fadeOut()
            }
        }) { targetCount ->
            Text("Count:$targetCount")
        }
    }
}

@Composable
fun AnimationSample4() {
    var message by remember {
        mutableStateOf("Hello")
    }
    Column {
        Button(onClick = {
            message += " China "
        }) {
            Text("Button")
        }

        Text(text = message, modifier = Modifier.animateContentSize())
    }
}


@Composable
fun AnimationSample5() {

    var size by remember {
        mutableStateOf(30.dp)
    }

    val sizeAnimation by animateDpAsState(
        targetValue = size,
        animationSpec = spring(Spring.DampingRatioHighBouncy)
    )

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier
                .size(sizeAnimation)
                .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                    size += 30.dp
                }
        )

    }

}

@Preview
@Composable
fun AnimationSamplePreview() {
    AnimationSample5()
}

