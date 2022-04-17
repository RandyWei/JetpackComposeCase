package icu.bughub.app.app.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import icu.bughub.app.app.model.entity.ArticleEntity
import icu.bughub.app.app.model.service.ArticleService
import kotlinx.coroutines.delay

class ArticleViewModel : ViewModel() {

    private val articleService = ArticleService.instance()

    private val pageSize = 10
    private var pageOffset = 1

    //文章列表数据
    var list by mutableStateOf(
        listOf(
            ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ),
            ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            )
        )
    )
        private set

    //第一页文章列表数据是否加载完成
    var listLoaded by mutableStateOf(false)
        private set

    //是否正在刷新
    var refreshing by mutableStateOf(false)
        private set

    //是否还有更多
    private var hasMore = false

    suspend fun fetchArticleList() {
        val res = articleService.list(pageOffset = pageOffset, pageSize = pageSize)
        if (res.code == 0 && res.data != null) {
            val tmpList = mutableListOf<ArticleEntity>()
            if (pageOffset != 1) {
                tmpList.addAll(list)
            }
            tmpList.addAll(res.data)
            //是否还有更多数据
            hasMore = res.data.size == pageSize
            list = tmpList
            listLoaded = true
            refreshing = false
        } else {
            pageOffset--
            if (pageOffset <= 1) {
                pageOffset = 1
            }
        }
    }

    /**
     * 下拉刷新
     *
     */
    suspend fun refresh() {
        pageOffset = 1
//        listLoaded = false
        refreshing = true
        fetchArticleList()
    }

    suspend fun loadMore() {
        if (hasMore) {
            pageOffset++
            fetchArticleList()
        }
    }

    //HTML 头部
    private val htmlHeader = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title></title>
            <style>
                img {
                    max-width: 100% !important;
                }
            </style>
        </head>
        <body>
    """.trimIndent()

    //html尾部
    private val htmlFooter = """
        </body>
        </html>
    """.trimIndent()


    private var articleEntity: ArticleEntity? = null

    var content by mutableStateOf(
        """$htmlHeader
        ${articleEntity?.content ?: ""}
        $htmlFooter
    """.trimIndent()
    )

    var infoLoaded by mutableStateOf(false)
        private set

    suspend fun fetchInfo() {
        val res = articleService.info("")
        if (res.code == 0 && res.data != null) {
            articleEntity = res.data
            content = """$htmlHeader
                            ${articleEntity?.content ?: ""}
                            $htmlFooter
                        """.trimIndent()
            infoLoaded = true
        }
    }
}