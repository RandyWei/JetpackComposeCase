package icu.bughub.app.composebasic.components


import androidx.compose.foundation.layout.Column
import androidx.compose.material.RadioButton
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun RadioButtonSample() {

    //单个按钮
//    var selected by remember {
//        mutableStateOf(false)
//    }
//
//    RadioButton(selected = selected, onClick = {
//        selected = !selected
//    })

    //多个按钮

    var checkedList by remember {

        mutableStateOf(
            listOf(
                false, false
            )
        )
    }

    Column() {

        checkedList.forEachIndexed { i, item ->
            RadioButton(selected = item, onClick = {
                checkedList = checkedList.mapIndexed { j, _ ->
                    i == j
                }

            })
        }

    }

}

@Preview
@Composable
fun RadioButtonSamplePreview() {
    RadioButtonSample()
}

