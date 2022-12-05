package com.example.procod.data.remote

import com.example.procod.model.*
import retrofit2.http.*

interface AppAPI {

    @POST("users")
    suspend fun register(
        @Body request: RegisterRequest
    )

    @POST("api/token")
    suspend fun login(
        @Body request: LoginRequest
    ): TokenResponse

    @GET("api/secured/ping")
    suspend fun authenticate(
        @Header("authorization") token: String
    )

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id:Int
    ): retrofit2.Response<UserData>

    companion object {
        const val BASE_URL = "http://10.0.2.2:3000/"
    }

}