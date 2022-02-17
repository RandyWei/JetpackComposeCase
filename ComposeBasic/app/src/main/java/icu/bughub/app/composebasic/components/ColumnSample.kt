package icu.bughub.app.composebasic.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ColumnSample() {

    Column(
        modifier = Modifier
            .size(200.dp)
            .background(Color.Green),
    ) {
        Text(
            text = "Column First Item",
            modifier = Modifier.weight(1f)
                .background(Color.Red)
        )
        Text(
            text = "Column Second Item",
            modifier = Modifier.weight(1f)
        )
    }

}

@Preview
@Composable
fun ColumnSamplePreview() {
    ColumnSample()
}

