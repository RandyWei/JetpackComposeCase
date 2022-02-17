package icu.bughub.app.composebasic.components


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ModifierSample() {
    Text(
        text = "中国人不骗中国人",
        modifier = Modifier
            .border(1.dp, Color.Red)
            .background(Color.Yellow)
            .padding(8.dp)
            .clickable {
                Log.i("====","你点击到我了")
            }
    )
}

@Preview
@Composable
fun ModifierSamplePreview() {
    ModifierSample()
}

