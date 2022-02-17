package icu.bughub.app.composebasic.components


import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun TextFieldSample() {

    var value by remember {
        mutableStateOf("")
    }

    TextField(
        value = value,
        onValueChange = {
            value = it
        },
        label = {
            Text("姓名")
        },
        placeholder = {
            Text("请在这里输入姓名")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
        },
        keyboardActions = KeyboardActions(onDone = {

        }),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        )

    )

}

@Preview
@Composable
fun TextFieldSamplePreview() {
    TextFieldSample()
}

