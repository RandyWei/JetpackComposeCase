package icu.bughub.app.app.model.service

import icu.bughub.app.app.model.Network
import icu.bughub.app.app.model.entity.ArticleInfoResponse
import icu.bughub.app.app.model.entity.ArticleListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("article/list")
    suspend fun list(
        @Query("pageOffset") pageOffset: Int,
        @Query("pageSize") pageSize: Int
    ): ArticleListResponse

    @GET("article/info")
    suspend fun info(@Query("id") id: String): ArticleInfoResponse

    companion object {
        fun instance(): ArticleService {
            return Network.createService(ArticleService::class.java)
        }
    }
}