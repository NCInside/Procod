package com.example.procod.data.remote

import com.example.procod.model.Code
import com.example.procod.model.CodeResult
import com.example.procod.model.JudgeTokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JudgeAPI {

    @POST("submissions?base64_encoded=false&wait=false")
    suspend fun postCode(
        @Body request: Code
    ): JudgeTokenResponse

    @GET("submissions/{token}?base64_encoded=false")
    suspend fun getCodeResult(
        @Path("token") token: String
    ): CodeResult

    companion object {
        const val BASE_URL = "http://192.168.1.13:2358/"
    }

}