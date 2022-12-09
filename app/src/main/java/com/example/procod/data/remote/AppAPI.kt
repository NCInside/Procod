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
        @Header("authorization") token: String,
        @Path("id") id: Int
    ): User

    @GET("users")
    suspend fun getUsers(
        @Header("authorization") token: String
    ): List<User>

    @GET("challenges")
    suspend fun getChallenges(
        @Header("authorization") token: String,
        @QueryMap hashMap: Map<String, Int>
    ): List<Challenge>

    @GET("challenges/{id}")
    suspend fun getChallenge(
        @Header("authorization") token: String,
        @Path("id") id: Int
    ): Challenge

    @GET("challenges/users/{id}")
    suspend fun getChallengesUser(
        @Header("authorization") token: String,
        @Path("id") id: Int
    ): List<Challenge>

    @GET("challenges/submission/{id}")
    suspend fun getChallengesSubmission(
        @Header("authorization") token: String,
        @Path("id") id: Int
    ): List<Challenge>

    @GET("statistics/{id}")
    suspend fun getStatistic(
        @Header("authorization") token: String,
        @Path("id") id: Int
    ): Statistic

    @GET("statistics/users/{id}")
    suspend fun getStatisticUser(
        @Header("authorization") token: String,
        @Path("id") id: Int
    ): Statistic

    @GET("statistics/all/{id}")
    suspend fun getStatistics(
        @Header("authorization") token: String,
        @Path("id") id: Int
    ): List<Statistic>

    @GET("statistics/users/all/{id}")
    suspend fun getStatisticsUserAll(
        @Header("authorization") token: String,
        @Path("id") id: Int
    ): List<Statistic>

    @POST("statistics")
    suspend fun postStatistic(
        @Body request: StatRequest
    )

    @PUT("statistics") //need checks
    suspend fun putStatistics(
        @Body request: StatRequest
    )

    @GET("labels")
    suspend fun getLabels(
        @Header("authorization") token: String
    ): List<Label>

    @POST("submissions")
    suspend fun postSubmission(
        @Body request: SubmitRequest
    )

    companion object {
        const val BASE_URL = "http://10.0.2.2:3000/"
    }

}