package icu.bughub.app.app.model.entity

/**
 * 分类
 *
 * @property title
 */
data class Category(
    val title: String,
    val id: String
)

/**
 * Category Response
 *
 * @property data
 */
data class CategoryResponse(var data: List<Category>) : BaseResponse() {}


