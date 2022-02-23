package icu.bughub.app.composebasic.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import icu.bughub.app.composebasic.R


@Composable
fun CanvasSample() {

    var imageBitmap: ImageBitmap? = null
    with(LocalContext.current) {
        imageBitmap = ImageBitmap.imageResource(id = R.drawable.newbanner4)
    }

    Canvas(modifier = Modifier.size(400.dp)) {

//        drawLine(
//            Color.Yellow,
//            start = Offset(0f, 10f),
//            end = Offset(100f, 200f),
//            strokeWidth = 100f,
//            cap = StrokeCap.Round
//        )
//
//        drawRect(
//            Color.Green,
//            topLeft = Offset(100f, 200f),
//            size = Size(100f, 100f)
//        )
//
////        imageBitmap?.let {
////            drawImage(it)
////        }
//
//        drawRoundRect(
//            Color.Green,
//            topLeft = Offset(100f, 200f),
//            size = Size(200f, 200f),
//            cornerRadius = CornerRadius(50f, 50f)
//        )
//
//        drawCircle(Color.Blue, style = Stroke(width = 10f))
//
//        drawOval(Color.Red, size = Size(300f, 200f))
//
//        drawArc(
//            Color.Magenta,
//            startAngle = 0f,
//            sweepAngle = 30f,
//            useCenter = true,
//            style = Stroke(10f)
//        )

        drawPoints(
            listOf(
                Offset(10f, 10f),
                Offset(20f, 50f),
                Offset(40f, 60f),
                Offset(60f, 100f)
            ),
            pointMode = PointMode.Points,
            color = Color.Magenta
        )

    }

}

@Preview
@Composable
fun CanvasSamplePreview() {
    CanvasSample()
}

