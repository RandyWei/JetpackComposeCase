package icu.bughub.app.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    var taskDate by mutableStateOf("学习周期:2022.01.01-2022.12.31")
        private set

    var totalPointOfYear = 13500

    //学年积分
    var pointOfYear by mutableStateOf(10000)
        private set

    //学年积分进度 = 220f * pointOfYear / 学年总积分
    var pointOfYearPercent by mutableStateOf(0f)
        private set

    /**
     * 更新学年积分进度
     *
     */
    fun updatePointPercent() {
        pointOfYearPercent = 220f * pointOfYear / totalPointOfYear
    }

    //一周积分情况
    var pointsOfWeek by mutableStateOf(listOf(0.0, 2.0, 6.0, 9.5, 10.0, 15.0, 5.0))
        private set

    //日期
    val weeks = listOf("02.05", "02.06", "02.07", "02.08", "02.09", "02.10", "今日")


    //今日积分
    private var todayPoint = 13

    //今日提醒文字
    var tips by mutableStateOf("今日获得0积分，快去完成下面任务吧")
        private set

    /**
     * 更新任务提醒文字
     *
     */
    fun updateTips() {
        tips = when (todayPoint) {
            0 -> {
                "今日获得0积分，快去完成下面任务吧"
            }
            in 1..14 -> {
                "今日获得${todayPoint}积分，快去完成下面任务吧"
            }
            else -> {
                "今日获得${todayPoint}积分，已经完成任务"
            }
        }
    }

}