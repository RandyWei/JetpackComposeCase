package icu.bughub.app.app.model.service

import icu.bughub.app.app.model.Network
import icu.bughub.app.app.model.entity.UserInfoResponse
import retrofit2.http.*

interface UserService {
    @FormUrlEncoded
    @POST("user/signIn")
    suspend fun signIn(
        @Field("userName") useName: String,
        @Field("password") password: String,
    ): UserInfoResponse

    companion object {
        fun instance(): UserService {
            return Network.createService(UserService::class.java)
        }
    }

}