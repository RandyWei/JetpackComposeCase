package icu.bughub.app.composebasic.components


import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun AnimationCanvasSample() {

    var endOffset = Offset(300f, 300f)

    val offsetAnimation by animateOffsetAsState(
        targetValue = endOffset,
        tween(durationMillis = 500, delayMillis = 500)
    )

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffeeeeee))
    ) {

        Surface(
            color = Color.White,
            elevation = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        ) {
            Text(text = "TOP")
        }

        Surface(
            color = Color.White,
            elevation = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .offset(y = 8.dp)
        ) {
            Text(text = "BOTTOM")
        }


//        Canvas(
//            modifier = Modifier
//                .size(300.dp)
//                .background(Color.Gray)
//        ) {
//            translate(offsetAnimation) {
//                drawLine(Color.Red, start = Offset.Zero, end = offsetAnimation)
//            }
//
//
//        }
    }

}

@Preview
@Composable
fun AnimationCanvasSamplePreview() {
    AnimationCanvasSample()
}

