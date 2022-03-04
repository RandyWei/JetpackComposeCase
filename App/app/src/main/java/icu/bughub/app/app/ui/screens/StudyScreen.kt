package icu.bughub.app.app.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import icu.bughub.app.app.ui.components.TopAppBar


@Composable
fun StudyScreen(statusBarHeight: Int) {
    Column(modifier = Modifier) {
        TopAppBar(statusBarHeight){
            Text("学习页")
        }
        Text("学习页")
    }
}

@Preview
@Composable
fun StudyScreenPreview() {
    StudyScreen(30)
}

