package icu.bughub.app.composebasic.components


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun StateSample() {

    var count by remember {
        mutableStateOf(1)
    }

    Log.d("====", "外面的值：${count}")

    Text(text = "我今天想叫${count}个小姐姐", modifier = Modifier
        .padding(8.dp)
        .clickable {
            count++
            Log.d("====", "我进来了")
        })


}

@Preview
@Composable
fun StateSamplePreview() {
    StateSample()
}

