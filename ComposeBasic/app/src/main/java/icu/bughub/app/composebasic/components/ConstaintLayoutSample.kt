package icu.bughub.app.composebasic.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet


@Composable
fun ConstraintLayoutSample() {

    var checked by remember {
        mutableStateOf(
            false
        )
    }

    val constraints = ConstraintSet {
        val icon = createRefFor("icon")
        val primaryText = createRefFor("primaryText")
        val secondlyText = createRefFor("secondlyText")
        val checkBox = createRefFor("checkBox")

        constrain(icon) {
            centerVerticallyTo(parent)
            start.linkTo(parent.start, margin = 8.dp)
        }

        constrain(primaryText) {
            start.linkTo(icon.end, margin = 8.dp)
            top.linkTo(parent.top, margin = 8.dp)
        }

        constrain(secondlyText) {
            start.linkTo(primaryText.start)
            top.linkTo(primaryText.bottom, margin = 8.dp)
            bottom.linkTo(parent.bottom, margin = 8.dp)
        }

        constrain(checkBox) {
            centerVerticallyTo(parent)
            end.linkTo(parent.end, margin = 8.dp)
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Yellow),
        constraintSet = constraints
    ) {

        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = null,
            modifier = Modifier.layoutId("icon")
        )

        Text(
            text = "Primary Text",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.layoutId("primaryText")
        )

        Text(
            text = "Secondly Text", color = Color.Gray,
            modifier = Modifier.layoutId("secondlyText")
        )

        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            modifier = Modifier.layoutId("checkBox")
        )
    }

}

@Preview
@Composable
fun ConstraintLayoutSamplePreview() {
    ConstraintLayoutSample()
}

