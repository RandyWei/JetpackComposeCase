package icu.bughub.app.composebasic.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SpacerSample() {

    Column(
        modifier = Modifier
            .size(200.dp)
            .background(Color.Green),
    ) {
        Text(
            text = "Column First Item",
            modifier = Modifier
                .background(Color.Red)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Column Second Item",
            modifier = Modifier
                .background(Color.Yellow)
        )
    }

}

@Preview
@Composable
fun SpacerSamplePreview() {
    SpacerSample()
}

