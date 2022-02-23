package icu.bughub.app.composebasic.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import icu.bughub.app.composebasic.R


@Composable
fun SurfaceSample() {
    //RectangleShape 矩形
    //RoundedCornerShape 圆角 ==》 CircleShape 50%圆角形成胶囊状
    //CutCornerShape 切角
    Surface(
//        modifier = Modifier.size(100.dp, 20.dp),
        shape = CutCornerShape(20),
        color = Color.Yellow,
        border = BorderStroke(1.dp, Color.Green),
        elevation = 10.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.newbanner4),
            contentDescription = null
        )
    }

}

@Preview
@Composable
fun SurfaceSamplePreview() {
    SurfaceSample()
}

