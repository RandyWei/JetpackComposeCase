package icu.bughub.app.composebasic.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun BoxSample() {

//    Box() {
//        Box(
//            modifier = Modifier
//                .size(200.dp)
//                .background(Color.Red)
//        )
//
//        Box(
//            modifier = Modifier
//                .align(Alignment.Center)
//                .size(100.dp)
//                .background(Color.Green)
//        )
//    }

    BoxWithConstraints() {

        if (maxWidth < maxHeight){
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Red)
            )
        } else {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(100.dp)
                    .background(Color.Green)
            )
        }

    }
}

@Preview
@Composable
fun BoxSamplePreview() {
    BoxSample()
}

