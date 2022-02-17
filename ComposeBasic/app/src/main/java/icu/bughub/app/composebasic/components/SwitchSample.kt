package icu.bughub.app.composebasic.components


import androidx.compose.material.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SwitchSample() {

    var checked by remember { mutableStateOf(false) }

    Switch(checked = checked, onCheckedChange = {
        checked = it
    })

}

@Preview
@Composable
fun SwitchSamplePreview() {
    SwitchSample()
}

