package icu.bughub.app.module.webview


import android.graphics.Bitmap
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect


@Composable
fun WebView(state: WebViewState) {

    var webView by remember {
        mutableStateOf<WebView?>(null)
    }

    //webview 变化或 state变化时重新订阅流数据
    LaunchedEffect(webView, state) {
        with(state) {
            //订阅流
            webView?.handleEvents()
        }
    }

    AndroidView(factory = { context ->
        WebView(context).apply {
            webChromeClient = object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    state.pageTitle = title
                }
            }

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    state.pageTitle = null
                }
            }

            with(settings) {
                javaScriptEnabled = true
            }
        }.also { webView = it }
    }) { view ->
        when (val content = state.content) {
            is WebContent.Url -> {
                val url = content.url
                //url 不空或者当前的 webview 加载的 url 不相同
                if (url.isNotEmpty() && url != view.url) {
                    view.loadUrl(content.url)
                }
            }
            is WebContent.Data -> {
                view.loadDataWithBaseURL(
                    content.baseUrl,
                    content.data,
                    null,
                    "utf-8",
                    null
                )
            }
        }
    }

}

sealed class WebContent() {
    data class Url(val url: String) : WebContent()
    data class Data(val data: String, val baseUrl: String? = null) : WebContent()
}

class WebViewState(private val coroutineScope: CoroutineScope, webContent: WebContent) {
    //网页内容：url 或者 data(html 内容)
    var content by mutableStateOf(webContent)

    var pageTitle: String? by mutableStateOf(null)
        internal set

    //事件类型
    private enum class EventType {
        EVALUATE_JAVASCRIPT //执行 JS 方法
    }

    //共享流的数据类型
    private class Event(val type: EventType, val args: String, val callback: ((String) -> Unit)?)

    //共享流
    private val events: MutableSharedFlow<Event> = MutableSharedFlow()

    internal suspend fun WebView.handleEvents(): Unit = withContext(Dispatchers.Main) {
        events.collect { event ->
            when (event.type) {
                EventType.EVALUATE_JAVASCRIPT -> evaluateJavascript(event.args, event.callback)
            }
        }
    }

    //执行 js 方法
    fun evaluateJavascript(script: String, resultCallback: ((String) -> Unit)? = {}) {
        val event = Event(EventType.EVALUATE_JAVASCRIPT, script, resultCallback)
        coroutineScope.launch { events.emit(event) } //推送流
    }
}

@Composable
fun rememberWebViewState(coroutineScope: CoroutineScope = rememberCoroutineScope(), url: String) =
    remember(key1 = url) {
        WebViewState(coroutineScope, WebContent.Url(url))
    }

@Composable
fun rememberWebViewState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    data: String,
    baseUrl: String? = null
) = remember(
    key1 = data,
    key2 = baseUrl
) {
    WebViewState(coroutineScope, WebContent.Data(data, baseUrl))
}

@Preview
@Composable
fun WebViewPreview() {
    WebView(rememberWebViewState(data = "<h1>Header</h1>"))
}

