package icu.bughub.app.app.extension

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*

@Composable
fun LazyListState.OnBottomReached(buffer: Int = 0, loadMore: () -> Unit) {

    require(buffer >= 0) { "buffer 值必须是正整数" }

    //是否应该加载更多的状态
    val shouldLoadMore = remember {
        //由另一个状态计算派生
        derivedStateOf {

            //获取最后显示的 item
            val lastVisibleItem =
                layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true

            //如果最后显示的 item 是最后一个 item 的话,说明已经触底，需要加载更多
            lastVisibleItem.index == layoutInfo.totalItemsCount - 1 - buffer

        }
    }

    LaunchedEffect(shouldLoadMore) {
        //详见文档：https://developer.android.com/jetpack/compose/side-effects#snapshotFlow
        snapshotFlow { shouldLoadMore.value }
            .collect {
                if (it) {
                    loadMore()
                }
            }
    }
}