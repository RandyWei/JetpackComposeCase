package icu.bughub.app.app.ui.components.video


import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.view.View
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.compose.AsyncImage
import java.util.*


@Composable
fun VideoPlayer(vodController: VodController) {

    var timeFormatText by remember {
        mutableStateOf("")
    }

    val MILLS_PER_SECOND = 1000
    val MILLS_PER_MINUTE = 60000
    val SECOND_PER_MINUTE = 60

    LaunchedEffect(vodController.playerValue.currentPosition) {
        val position = vodController.playerValue.currentPosition
        val duration = vodController.playerValue.duration

        //格式化时间
        timeFormatText =
            String.format(
                "%02d:%02d:%02d / %02d:%02d:%02d",
                position / MILLS_PER_MINUTE / SECOND_PER_MINUTE,
                position / MILLS_PER_MINUTE,
                position / MILLS_PER_SECOND % SECOND_PER_MINUTE,
                duration / MILLS_PER_MINUTE / SECOND_PER_MINUTE,
                duration / MILLS_PER_MINUTE,
                duration / MILLS_PER_SECOND % SECOND_PER_MINUTE
            )
    }

    //是否显示控制层
    var showControlBar by remember {
        mutableStateOf(false)
    }

    var timer: Timer? = null

    val configuration = LocalConfiguration.current
    val context = LocalContext.current

    //监听生命周期
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(vodController) {
        val lifecycleEventObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> vodController.resume()
                Lifecycle.Event.ON_PAUSE -> vodController.pause()
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(lifecycleEventObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleEventObserver)
            vodController.stopPlay()
        }
    }

    //当处于横屏状态时，启用 BackHandler 监听物理返回，回到竖屏状态。竖屏状态禁用 BackHandler
    BackHandler(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            context.findActivity()?.requestedOrientation =
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    Box(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
        ) {
            timer?.cancel()
            timer = null
            if (!showControlBar) {
                timer = Timer()
                timer?.schedule(object : TimerTask() {
                    override fun run() {
                        showControlBar = false
                    }
                }, 3000)
            }

            showControlBar = !showControlBar
        }) {

        //视频播放层
        VideoView(vodPlayer = vodController.vodPlayer)

        //视频封面
        if (vodController.playerValue.state == PlayState.None) {
            Box() {
                AsyncImage(
                    model = vodController.playerValue.coverUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                )

                IconButton(
                    onClick = {
                        vodController.startPlay()
                    },
                    modifier = Modifier
                        .align(Alignment.Center),
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                    )
                }
            }
        }

        //正在加载层
        if (vodController.playerValue.state == PlayState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(60.dp)
            )
        }

        //视频控制层
        if (showControlBar) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Spacer(modifier = Modifier.height(1.dp))
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(45.dp)
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Black,
                                        Color.Transparent
                                    )
                                )
                            )
                    ) {
                        IconButton(onClick = {
                            context.findActivity()?.requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }

                        Text(vodController.playerValue.title ?: "", color = Color.White)
                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black))),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //播放或暂停按钮
                    IconButton(onClick = {
                        if (vodController.playerValue.state == PlayState.Playing) {
                            vodController.pause()
                        } else {
                            vodController.resume()
                        }
                    }) {
                        if (vodController.playerValue.state == PlayState.Playing) {
                            Icon(
                                imageVector = Icons.Default.Pause,
                                contentDescription = null,
                                tint = Color.White
                            )
                        } else {

                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    //进度条
                    Slider(
                        value = vodController.playerValue.currentPosition.toFloat(),
                        onValueChange = {
                            vodController.playerValue.currentPosition = it.toLong()
                            vodController.seekTo(it.toLong())
                        },
                        valueRange = 0f..vodController.playerValue.duration.toFloat(),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    //时间显示
                    Text(text = timeFormatText, color = Color.White, fontSize = 14.sp)

                    Spacer(modifier = Modifier.width(8.dp))

                    //控制全屏按钮
                    IconButton(onClick = {
                        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                            context.findActivity()?.requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                            context.findActivity()?.window?.decorView?.systemUiVisibility =
                                (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_IMMERSIVE)
                        } else {
                            context.findActivity()?.requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                    }) {
                        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                            Icon(
                                imageVector = Icons.Default.Fullscreen,
                                contentDescription = null,
                                tint = Color.White
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.FullscreenExit,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }

                    }

                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }

}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

