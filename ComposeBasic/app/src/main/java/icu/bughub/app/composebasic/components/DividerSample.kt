package icu.bughub.app.composebasic.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DividerSample() {

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

        Divider(
            startIndent = 4.dp,
            thickness = 10.dp,
            color = Color.Magenta
        )

        Text(
            text = "Column Second Item",
            modifier = Modifier
                .background(Color.Yellow)
        )
    }

}

@Preview
@Composable
fun DividerSamplePreview() {
    DividerSample()
}

