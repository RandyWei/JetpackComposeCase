package icu.bughub.app.composebasic.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyRowSample() {

    val data = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)



    LazyRow() {

        stickyHeader {
            Text(
                text = "Header",
                modifier = Modifier.background(Color.Yellow)
            )
        }

        items(data) {

            Text(
                "Row Item $it",
                modifier = Modifier.padding(8.dp)
            )

        }

    }

}

@Preview
@Composable
fun LazyRowSamplePreview() {
    LazyRowSample()
}

