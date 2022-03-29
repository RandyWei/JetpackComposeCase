package icu.bughub.app.app.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import com.tencent.rtmp.TXVodPlayer
import icu.bughub.app.app.ui.components.video.VideoView
import icu.bughub.app.app.viewmodel.VideoViewModel
import icu.bughub.app.module.webview.WebView
import icu.bughub.app.module.webview.rememberWebViewState


@Composable
fun VideoDetailScreen(videoViewModel: VideoViewModel = viewModel(), onBack: () -> Unit) {

    val webViewState = rememberWebViewState(data = videoViewModel.videoDesc)

    val vodPlayer = TXVodPlayer(LocalContext.current)

    LaunchedEffect(vodPlayer) {
        //http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4
        vodPlayer.startPlay("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "视频详情",
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.NavigateBefore,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                onBack()
                            }
                            .padding(8.dp)
                    )
                },
            )
        },
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .statusBarsPadding(),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            //视频区域
            Box(modifier = Modifier.height(200.dp)) {
                VideoView(vodPlayer = vodPlayer)
            }

            //想让标题一起滚动，有两个方案
            //方案一：把标题放到视频简介的 html 文本中去
            //方案二：计算 视频简介在 webview 中的高度，然后动态设置 webview 的高度
            WebView(state = webViewState)
        }
    }

}


