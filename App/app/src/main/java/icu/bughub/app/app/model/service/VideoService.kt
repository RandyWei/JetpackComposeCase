package icu.bughub.app.app.model.service

import icu.bughub.app.app.model.Network
import icu.bughub.app.app.model.entity.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {

    @GET("video/list")
    suspend fun list(
        @Query("pageOffset") pageOffset: Int,
        @Query("pageSize") pageSize: Int
    ): VideoListResponse

    companion object {
        fun instance(): VideoService {
            return Network.createService(VideoService::class.java)
        }
    }

}