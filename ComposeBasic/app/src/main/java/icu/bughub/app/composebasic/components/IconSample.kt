package icu.bughub.app.composebasic.components


import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Translate
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import icu.bughub.app.composebasic.R


@Composable
fun IconSample() {
//    Icon(imageVector = Icons.Default.Translate, contentDescription = null, tint = Color.Red)

    IconButton(onClick = {  }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_android_black_24dp),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun IconSamplePreview() {
    IconSample()
}

