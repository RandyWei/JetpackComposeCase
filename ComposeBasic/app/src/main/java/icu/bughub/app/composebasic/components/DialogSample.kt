package icu.bughub.app.composebasic.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun DialogSample() {

    var showDialog by remember {
        mutableStateOf(false)
    }

    Column() {
        Button(onClick = {
            showDialog = true
        }) {
            Text("show dialog")
        }
        if (showDialog) {
//            Dialog(
//                onDismissRequest = { showDialog = false }
//            ) {
//                Surface(
//                    color = Color.White,
//                    modifier = Modifier.size(
//                        200.dp, 100.dp
//                    )
//                ) {
//                    Column() {
//                        Text("Dialog Content")
//                    }
//                }
//            }

            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text("Title")
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                },
                text = {
                    Text("这是 Dialog 的内容")
                }
            )

        }

    }

}

@Preview
@Composable
fun DialogSamplePreview() {
    DialogSample()
}

