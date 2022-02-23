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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun ConstraintsSample() {

    var checked by remember {
        mutableStateOf(
            false
        )
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Yellow)
    ) {
        val (icon, primaryText, secondlyText, checkBox) = createRefs()

        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = null,
            modifier = Modifier.constrainAs(icon) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start, 8.dp)
            }
        )

        Text(
            text = "Primary Text",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(primaryText) {
                start.linkTo(icon.end, margin = 8.dp)
                top.linkTo(parent.top)
            }
        )

        Text(text = "Secondly Text", color = Color.Gray,
            modifier = Modifier.constrainAs(secondlyText) {
                start.linkTo(primaryText.start)
                top.linkTo(primaryText.bottom, margin = 8.dp)
                bottom.linkTo(parent.bottom)
            })

        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            modifier = Modifier.constrainAs(checkBox) {
                centerVerticallyTo(parent)
                end.linkTo(parent.end, margin = 8.dp)
            }
        )
    }

}

@Preview
@Composable
fun ConstraintsSamplePreview() {
    ConstraintsSample()
}

