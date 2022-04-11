package icu.bughub.app.app.model.service

import icu.bughub.app.app.model.Network
import icu.bughub.app.app.model.entity.CategoryResponse
import icu.bughub.app.app.model.entity.SwiperResponse
import retrofit2.http.GET

interface HomeService {

    @GET("category/list")
    suspend fun category(): CategoryResponse

    @GET("recommand/banner")
    suspend fun banner(): SwiperResponse

    companion object {
        fun instance(): HomeService {
            return Network.createService(HomeService::class.java)
        }
    }


}