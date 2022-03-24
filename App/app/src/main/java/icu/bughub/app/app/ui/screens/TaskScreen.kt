package icu.bughub.app.app.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import icu.bughub.app.app.ui.components.ChartView
import icu.bughub.app.app.ui.components.CircleRing
import icu.bughub.app.app.ui.components.DailyTaskContent
import icu.bughub.app.app.ui.components.appBarHeight
import icu.bughub.app.app.viewmodel.TaskViewModel


@Composable
fun TaskScreen(taskVM: TaskViewModel = viewModel()) {

    //圆环高度
    var boxWidthDp: Int
    with(LocalConfiguration.current) {
        boxWidthDp = screenWidthDp / 2
    }

    //当学年积分改变时重新计算百分比
    LaunchedEffect(taskVM.pointOfYear) {
        taskVM.updatePointPercent()
        taskVM.updateTips()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF149EE7), Color.White)
                )
            )
    ) {
        //标题栏
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .height(appBarHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "学习任务",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 18.sp,
            )
        }

        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            //学习周期
            item {
                Text(
                    text = taskVM.taskDate,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }

            //学习进度
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(boxWidthDp.dp)
                        .padding(top = 8.dp)
                ) {
                    //圆环
                    CircleRing(boxWidthDp, taskVM)

                    //进度数据
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            buildAnnotatedString {
                                append(taskVM.pointOfYear.toString())
                                withStyle(SpanStyle(fontSize = 12.sp)) {
                                    append("分")
                                }
                            },
                            fontSize = 36.sp,
                            color = Color.White
                        )
                        Text(
                            text = "学年积分",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-40).dp)
                ) {
                    Column() {
                        Text(
                            text = "${taskVM.totalPointOfYear}分",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "学年规定积分",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                    Column() {
                        Text(
                            text = "${taskVM.totalPointOfYear - taskVM.pointOfYear}分",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "还差",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }

            //学习明细
            item {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(Color.White)
                        .fillMaxSize()
                        .padding(top = 8.dp)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "学习明细",
                        fontSize = 16.sp,
                        color = Color(0xFF333333)
                    )
                    Text(
                        text = "最近一周获得积分情况",
                        fontSize = 14.sp,
                        color = Color(0xFF999999)
                    )

                    //积分情况的折线图
                    ChartView(taskVM.pointsOfWeek, modifier = Modifier.padding(vertical = 8.dp))

                    //日期
                    Row() {
                        taskVM.weeks.forEach {
                            Text(
                                text = it,
                                fontSize = 12.sp,
                                color = Color(0xFF999999),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    //今日任务提醒
                    Text(
                        text = taskVM.tips,
                        color = Color(0xFF149EE7),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                Color(0x33149EE7)
                            )
                            .padding(8.dp)
                            .fillMaxWidth()
                    )

                    //每日任务
                    DailyTaskContent()

                }
            }
        }

    }
}

@Preview
@Composable
fun TaskScreenPreview() {
    TaskScreen()
}

