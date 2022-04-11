package icu.bughub.app.app.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SwiperEntity(
    @Json(name = "imgUrl") val imageUrl: String,
    val title: String? = ""
)


data class SwiperResponse(val data: List<SwiperEntity>?) : BaseResponse()