package com.example.procod.data.repository

import android.content.SharedPreferences
import com.example.procod.data.remote.AppAPI
import com.example.procod.model.LoginRequest
import com.example.procod.model.RegisterRequest
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

    suspend fun getUser(id: Int) = api.getUser(id)

}