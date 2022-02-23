package icu.bughub.app.composebasic.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.max


@Composable
fun Stepper(onStep: ((Int) -> Unit)? = null) {

    var step by remember {
        mutableStateOf("0")
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(0.5.dp, Color.Gray, shape = RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(6.dp))
            .background(Color.White)
            .height(IntrinsicSize.Min)
            .heightIn(30.dp)
    ) {

        Icon(
            imageVector = Icons.Default.ArrowLeft,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .background(Color.Blue)
                .fillMaxHeight()
                .clickable {
                    step = max(0, (step.toInt() - 1)).toString()
                }
        )

        BasicTextField(
            value = step,
            onValueChange = { value ->
                step = value
                if (value.isNotEmpty())
                    onStep?.let {
                        it(value.toInt())
                    }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                lineHeight = 14.sp,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .background(Color.White)
                .padding(0.dp)
                .width(IntrinsicSize.Min)
                .widthIn(30.dp, 100.dp)
        )

        Icon(imageVector = Icons.Default.ArrowRight, contentDescription = null, tint = Color.White,
            modifier = Modifier
                .background(Color.Blue)
                .fillMaxHeight()
                .clickable {
                    step = (step.toInt() + 1).toString()
                })
    }

}


@Preview
@Composable
fun StepperPreview() {
    Stepper()
}

