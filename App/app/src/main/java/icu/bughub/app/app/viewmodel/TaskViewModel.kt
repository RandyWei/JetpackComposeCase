package icu.bughub.app.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    var taskDate by mutableStateOf("学习周期:2022.01.01-2022.12.31")
        private set

    var pointOfYear by mutableStateOf("10000")
        private set
}