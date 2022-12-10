package com.example.procod.data.repository

import com.example.procod.data.remote.JudgeAPI
import com.example.procod.model.Code
import com.example.procod.model.CodeResult
import com.example.procod.model.JudgeTokenResponse
import com.example.procod.util.AuthResult
import retrofit2.HttpException
import javax.inject.Inject

class JudgeRepository @Inject constructor(
    private val api: JudgeAPI
) {

    suspend fun submitCode(
        code: String,
        stdin: String
    ): AuthResult<JudgeTokenResponse> {
        return try {
            val result = api.postCode(
                request = Code(
                    source_code = code,
                    stdin = stdin
                )
            )
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

    suspend fun getResult(
        token: String
    ): AuthResult<CodeResult> {
        return try {
            val result = api.getCodeResult(token)
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