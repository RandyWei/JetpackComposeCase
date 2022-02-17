package icu.bughub.app.composebasic.components


import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ProgressIndicatorSample() {

//    CircularProgressIndicator(
//        color = Color.Red,
//        progress = 0.5f
//    )

    LinearProgressIndicator(
        color = Color.Red, backgroundColor = Color.Green, progress = 0.5f
    )

}

@Preview
@Composable
fun ProgressIndicatorSamplePreview() {
    ProgressIndicatorSample()
}

