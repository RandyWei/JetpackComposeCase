package icu.bughub.app.app.ui.components


import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import icu.bughub.app.app.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.*


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwiperContent(vm: MainViewModel) {

    //虚拟页数
    val virtualCount = Int.MAX_VALUE

    //实际页数
    val actualCount = vm.swiperData.size

    //初始图片下标
    val initialIndex = virtualCount / 2

    val pagerState = rememberPagerState(initialPage = initialIndex)

    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {

        coroutineScope.launch {
            vm.swiperData()
        }

        val timer = Timer()

        timer.schedule(object : TimerTask() {
            override fun run() {
                if (vm.swiperLoaded) {
                    coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                }
            }
        }, 3000, 3000)

        onDispose {
            timer.cancel()
        }
    }

    HorizontalPager(
        count = virtualCount,
        state = pagerState,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp)),
        userScrollEnabled = vm.swiperLoaded
    ) { index ->
        val actualIndex =
            (index - initialIndex).floorMod(actualCount) //index - (index.floorDiv(actualCount)) * actualCount

        AsyncImage(
            model = vm.swiperData[actualIndex].imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(7 / 3f)
                .placeholder(
                    visible = !vm.swiperLoaded,
                    highlight = PlaceholderHighlight.shimmer()
                ),
            contentScale = ContentScale.Crop
        )
    }
}

fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    //将虚拟数据按照实际数据总数分为 N 组
    //当前虚拟下标是在这虚拟数据中的哪一组：虚拟下标floorDiv实际数据总数(虚拟下标/实际数据总数)。向下取整
    //虚拟下标 - (虚拟下标/实际数据总数) * 实际数据总数
    else -> this - floorDiv(other) * other
}

