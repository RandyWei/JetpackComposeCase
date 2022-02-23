package icu.bughub.app.composebasic.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyVerticalGridSample() {

    val data = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    LazyVerticalGrid(cells = GridCells.Adaptive(100.dp)) {
        items(data) {
            Card() {
                Text(
                    "Grid Item $it",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

}

@Preview
@Composable
fun LazyVerticalGridSamplePreview() {
    LazyVerticalGridSample()
}

