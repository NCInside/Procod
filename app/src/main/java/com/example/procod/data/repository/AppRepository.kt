package com.example.procod.data.repository

import android.content.SharedPreferences
import com.example.procod.data.remote.AppAPI
import com.example.procod.model.*
import com.example.procod.util.AuthResult
import retrofit2.HttpException
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val api: AppAPI,
    private val prefs: SharedPreferences
) {

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): AuthResult<Unit> {
        return try {
            api.register(
                request = RegisterRequest(
                    username = username,
                    email = email,
                    password = password
                )
            )
            login(email, password)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    suspend fun login(email: String, password: String): AuthResult<Unit> {
        return try {
            val response = api.login(
                request = LoginRequest(
                    email = email,
                    password = password
                )
            )
            prefs.edit()
                .putString("jwt", response.token)
                .putInt("id", response.id)
                .apply()
            AuthResult.Authorized()
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.authenticate(token)
            AuthResult.Authorized()
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    suspend fun getUser(userid: Int?) : AuthResult<User> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val id = userid ?: prefs.getInt("id", -1)
            val result = api.getUser(token, id)
            AuthResult.Authorized(result)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    suspend fun getUsers() : AuthResult<List<User>> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val result = api.getUsers(token)
            AuthResult.Authorized(result)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    suspend fun getChallenges(id: Int?) : AuthResult<List<Challenge>> {
        return try {
            val hashMap = HashMap<String, Int>()
            if (id != null) hashMap["label"] = id
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val result = api.getChallenges(token, hashMap)
            AuthResult.Authorized(result)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    suspend fun getChallengesSubmission() : AuthResult<List<Challenge>> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val id = prefs.getInt("id", -1)
            val result = api.getChallengesSubmission(token, id)
            AuthResult.Authorized(result)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    suspend fun getChallenge(id: Int) : AuthResult<Challenge> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val result = api.getChallenge(token, id)
            AuthResult.Authorized(result)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    suspend fun getStatisticsUser() : AuthResult<List<Statistic>> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val id = prefs.getInt("id", -1)
            val result = api.getStatisticsUserAll(token, id)
            AuthResult.Authorized(result)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    suspend fun getStatisticUser() : AuthResult<Statistic> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val id = prefs.getInt("id", -1)
            val result = api.getStatisticUser(token, id)
            AuthResult.Authorized(result)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    suspend fun getLabels() : AuthResult<List<Label>> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val result = api.getLabels(token)
            AuthResult.Authorized(result)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    suspend fun postSubmission(
        code: String,
        runtime: Int,
        is_correct: Boolean,
        error: String,
        memory: Int,
        challengeid: Int

    ) : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val id = prefs.getInt("id", -1)
            api.postSubmission(
                token = token,
                request = SubmitRequest(
                    Code = code,
                    Runtime = runtime,
                    Is_correct = is_correct,
                    Error = error,
                    Memory = memory,
                    UserID = id,
                    ChallengeID = challengeid
                )
            )
            AuthResult.Authorized()
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    suspend fun postStatistic(
        num_challenge_attempted: Int,
        num_challenge_completed: Int,
        num_challenge_made: Int,
        total_memory: Int,
        total_runtime: Int,
    ) : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val id = prefs.getInt("id", -1)
            api.postStatistic(
                token = token,
                request = StatRequest(
                    Num_challenge_attempted = num_challenge_attempted,
                    Num_challenge_completed = num_challenge_completed,
                    Num_challenge_made = num_challenge_made,
                    Total_memory = total_memory,
                    Total_runtime = total_runtime,
                    UserID = id
                )
            )
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

}