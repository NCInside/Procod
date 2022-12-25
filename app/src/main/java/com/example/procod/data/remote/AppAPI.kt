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

    @PUT("users/{id}")
    suspend fun putUser(
        @Header("authorization") token: String,
        @Path("id") id: Int,
        @Body request: RegisterRequest
    ): User

    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Header("authorization") token: String,
        @Path("id") id: Int,
    )

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

    @POST("challenges")
    suspend fun postChallenge(
        @Header("authorization") token: String,
        @Body request: ChallRequest
    ) : Challenge

    @PUT("challenges/{id}")
    suspend fun putChallenge(
        @Header("authorization") token: String,
        @Path("id") id: Int,
        @Body request: ChallRequest
    )

    @DELETE("challenges/{id}")
    suspend fun deleteChallenge(
        @Header("authorization") token: String,
        @Path("id") id: Int
    )

    @POST("challenges/example")
    suspend fun postChallengeExample(
        @Header("authorization") token: String,
        @Body request: ExamRequest
    )

    @PUT("challenges/example/{id}")
    suspend fun putChallengeExample(
        @Header("authorization") token: String,
        @Path("id") id: Int,
        @Body request: ExamRequest
    )

    @DELETE("challenges/example/{id}")
    suspend fun deleteChallengeExample(
        @Header("authorization") token: String,
        @Path("id") id: Int
    )

    @POST("challenges/target")
    suspend fun postChallengeTarget(
        @Header("authorization") token: String,
        @Body request: TargetRequest
    )

    @PUT("challenges/target/{id}")
    suspend fun putChallengeTarget(
        @Header("authorization") token: String,
        @Path("id") id: Int,
        @Body request: TargetRequest
    )

    @DELETE("challenges/target/{id}")
    suspend fun deleteChallengeTarget(
        @Header("authorization") token: String,
        @Path("id") id: Int
    )

    @POST("challenges/label")
    suspend fun postChallengeLabel(
        @Header("authorization") token: String,
        @Query("challengeid") challengeId: Int,
        @Query("labelid") labelId: Int
    )

    @DELETE("challenges/label")
    suspend fun deleteChallengeLabel(
        @Header("authorization") token: String,
        @Query("challengeid") challengeId: Int,
        @Query("labelid") labelId: Int
    )

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
        @Header("authorization") token: String,
        @Body request: StatRequest
    )

    @PUT("statistics") //need checks
    suspend fun putStatistics(
        @Header("authorization") token: String,
        @Body request: StatRequest
    )

    @GET("labels")
    suspend fun getLabels(
        @Header("authorization") token: String
    ): List<Label>

    @POST("submissions")
    suspend fun postSubmission(
        @Header("authorization") token: String,
        @Body request: SubmitRequest
    )

    companion object {
        const val BASE_URL = "http://192.168.1.13:3000/"
    }

}