package icu.bughub.app.app.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleEntity(
    val title: String,
    var source: String,
    @Json(name = "time")
    var timestamp: String,
    var content: String? = ""
)

data class ArticleListResponse(val data: List<ArticleEntity>?) : BaseResponse()

data class ArticleInfoResponse(val data: ArticleEntity?) : BaseResponse()