package icu.bughub.app.composebasic.components


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.coroutineScope


@Composable
fun LifecycleSample() {

    var count by remember {
        mutableStateOf(0)
    }

//    LaunchedEffect(Unit) {
//        Log.i("======", "LaunchedEffect")
//    }
//
//    Log.i("======", "LifecycleSample")

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {

        val lifecycleEventObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    //进入后台，调用暂停方法
                }
                Lifecycle.Event.ON_RESUME -> {
                    //回到前台 ，调用播放
                }
                else -> {

                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleEventObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleEventObserver)
        }
    }

    Column() {
        Text("我叫了${count}个小姐姐")

        Button(onClick = {
            count++
        }) {
            Text("Button")
        }

        if (count == 3) {
            SubScreen(count)
        }
    }

}

@Composable
fun SubScreen(count: Int) {

    DisposableEffect(Unit) {

        Log.i("======", "DisposableEffect")

        onDispose {
            Log.i("======", "DisposableEffect:onDispose")
        }
    }

    Text(text = "我儿子也叫了${count}个小姐姐")
}

@Preview
@Composable
fun LifecycleSamplePreview() {
    LifecycleSample()
}

