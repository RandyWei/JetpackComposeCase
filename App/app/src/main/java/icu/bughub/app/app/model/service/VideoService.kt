package icu.bughub.app.app.model.service

import icu.bughub.app.app.model.Network
import icu.bughub.app.app.model.entity.VideoInfoResponse
import icu.bughub.app.app.model.entity.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {

    @GET("video/list")
    suspend fun list(
        @Query("pageOffset") pageOffset: Int,
        @Query("pageSize") pageSize: Int
    ): VideoListResponse

    @GET("video/info")
    suspend fun info(
        @Query("id") id: String
    ): VideoInfoResponse

    companion object {
        fun instance(): VideoService {
            return Network.createService(VideoService::class.java)
        }
    }

}