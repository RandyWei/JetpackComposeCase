package icu.bughub.app.app.ui.screens


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tencent.rtmp.TXVodPlayer
import icu.bughub.app.app.ui.components.video.VideoPlayer
import icu.bughub.app.app.ui.components.video.VideoView
import icu.bughub.app.app.ui.components.video.rememberVodController
import icu.bughub.app.app.ui.theme.Blue700
import icu.bughub.app.app.viewmodel.VideoViewModel
import icu.bughub.app.module.webview.WebView
import icu.bughub.app.module.webview.rememberWebViewState


@Composable
fun VideoDetailScreen(videoViewModel: VideoViewModel = viewModel(), onBack: () -> Unit) {

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        videoViewModel.fetchInfo()
    }

    val webViewState = rememberWebViewState(data = videoViewModel.videoDesc)


    val configuration = LocalConfiguration.current

    var scaffoldModifier by remember {
        mutableStateOf(Modifier.alpha(1f))
    }

    var videoBoxModifier by remember {
        mutableStateOf(
            Modifier.aspectRatio(16 / 9f)
        )
    }


    Scaffold(
        topBar = {
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                TopAppBar(
                    title = {
                        Text(
                            text = "????????????",
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
            }
        },
        modifier = scaffoldModifier,
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (videoViewModel.infoLoaded) {
                Column(modifier = Modifier.fillMaxSize()) {
                    val vodController =
                        rememberVodController(
                            videoUrl = videoViewModel.videoUrl,
                            coverUrl = videoViewModel.coverUrl
                        )
                    //TODO ??????????????????????????????????????????????????????????????????????????????????????????????????????
                    LaunchedEffect(configuration.orientation) {
                        vodController.restore()
                        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                            videoBoxModifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16 / 9f)
                            systemUiController.isSystemBarsVisible = true

                            scaffoldModifier = Modifier
                                .background(Blue700)
                                .statusBarsPadding()

                        } else {
                            videoBoxModifier = Modifier
                                .fillMaxSize()
                            systemUiController.isSystemBarsVisible = false

                            scaffoldModifier = Modifier
                        }
                    }

                    //????????????
                    Box(modifier = videoBoxModifier) {
                        VideoPlayer(vodController = vodController)
                    }

                    //??????????????????????????????????????????
                    //?????????????????????????????????????????? html ????????????
                    //?????????????????? ??????????????? webview ????????????????????????????????? webview ?????????
                    WebView(state = webViewState)
                }
            } else {
                CircularProgressIndicator()
            }
        }
    }

}


