package icu.bughub.app.app.ui.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import icu.bughub.app.app.viewmodel.TaskViewModel


@Composable
fun CircleRing(boxWidthDp: Int, vm: TaskViewModel) {

    val strokeWidth = 30f

    Canvas(modifier = Modifier.size(boxWidthDp.dp)) {
        //两个方案
        //1、使用startAngle = -10f 和 sweepAngle = 220f 并使用 rotate(180f)进行翻转
        //2、startAngle = 160f,sweepAngle = 220f,
        drawArc(
            Color(0, 0, 0, 15),
            startAngle = 160f,
            sweepAngle = 220f,
            useCenter = false,
            style = Stroke(strokeWidth, cap = StrokeCap.Round)
        )

        drawArc(
            Color.White,
            startAngle = 160f,
            sweepAngle = vm.pointOfYearPercent,
            useCenter = false,
            style = Stroke(strokeWidth, cap = StrokeCap.Round)
        )
    }

}


