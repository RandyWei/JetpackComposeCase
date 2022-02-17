package icu.bughub.app.composebasic.components


import androidx.compose.foundation.layout.Column
import androidx.compose.material.Checkbox
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CheckBoxSample() {

//    var checked by remember {
//        mutableStateOf(false)
//    }
//
//    Checkbox(checked = checked, onCheckedChange = {
//        checked = it
//    })

    var checkedList by remember {
        mutableStateOf(listOf(false, false))
    }


    Column {

        checkedList.forEachIndexed { i, item ->

            Checkbox(checked = item, onCheckedChange = {

                checkedList = checkedList.mapIndexed { j, b ->

                    if (i == j) {
                        !b
                    } else {
                        b
                    }

                }

            })

        }

    }


}

@Preview
@Composable
fun CheckBoxSamplePreview() {
    CheckBoxSample()
}

