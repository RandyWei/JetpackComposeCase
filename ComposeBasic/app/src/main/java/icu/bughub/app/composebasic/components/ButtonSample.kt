package icu.bughub.app.composebasic.components


import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ButtonSample() {
//    Button(onClick = {
//        Log.d("====", "我是中国人")
//    }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {
//        Text("不点不是中国人")
//    }

//    TextButton(onClick = { }) {
//        Text("不点不是中国人")
//    }

    OutlinedButton(onClick = { }) {
        Text("不点不是中国人")
    }
}

@Preview
@Composable
fun ButtonSamplePreview() {
    ButtonSample()
}

