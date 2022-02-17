package icu.bughub.app.composebasic.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CardSample() {

    Card(
        backgroundColor = Color.Green,
        contentColor = Color.Red,
        border = BorderStroke(1.dp, Color.Red),
        elevation = 10.dp
    ) {
        Text(
            "Card Sample",
            modifier = Modifier.padding(8.dp),
        )
    }

}

@Preview
@Composable
fun CardSamplePreview() {
    CardSample()
}

