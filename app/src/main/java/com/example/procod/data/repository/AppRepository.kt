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

    suspend fun getStatistic() : AuthResult<Statistic> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val id = prefs.getInt("id", -1)
            val result = api.getStatistic(token, id)
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

}