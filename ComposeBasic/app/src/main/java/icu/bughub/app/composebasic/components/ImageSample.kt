package icu.bughub.app.composebasic.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import icu.bughub.app.composebasic.R


@Composable
fun ImageSample() {
    Image(
        painter = painterResource(id = R.drawable.newbanner4),
        contentDescription = null,
        modifier = Modifier.size(50.dp),
        colorFilter = ColorFilter.tint(Color.Red, blendMode = BlendMode.Color)
    )
}

@Preview
@Composable
fun ImageSamplePreview() {
    ImageSample()
}

