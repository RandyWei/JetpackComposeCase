package icu.bughub.app.app.ui.components.video


import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.tencent.rtmp.TXVodPlayer
import com.tencent.rtmp.ui.TXCloudVideoView
import icu.bughub.app.app.R


@Composable
fun VideoView(vodPlayer: TXVodPlayer) {

    AndroidView(factory = { context ->
        (LayoutInflater.from(context).inflate(R.layout.video, null, false)
            .findViewById(R.id.videoView) as TXCloudVideoView).apply {
            vodPlayer.setPlayerView(this)
        }
    })

}

