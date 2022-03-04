package icu.bughub.app.app.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import icu.bughub.app.app.ui.components.TopAppBar


@Composable
fun MineScreen(statusBarHeight: Int) {
    Column(modifier = Modifier) {
        TopAppBar(statusBarHeight) {
            Text(text = "我的页面")
        }
        Text(text = "我的页面")
    }
}

@Preview
@Composable
fun MineScreenPreview() {
    MineScreen(20)
}

