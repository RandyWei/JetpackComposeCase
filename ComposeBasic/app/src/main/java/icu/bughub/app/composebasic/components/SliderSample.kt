package icu.bughub.app.composebasic.components


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SliderSample() {

//    var value by remember {
//        mutableStateOf(0f)
//    }
//
//    Slider(value = value, onValueChange = {
//        value = it
//    }, valueRange = 0f..100f, steps = 4)

    var values by remember {
        mutableStateOf(0.2f..0.5f)
    }

    RangeSlider(values = values, onValueChange = { values = it })

}

@Preview
@Composable
fun SliderSamplePreview() {
    SliderSample()
}

