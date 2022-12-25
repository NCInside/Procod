package com.example.procod.data.repository

import android.content.SharedPreferences
import android.icu.text.CaseMap.Title
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

    fun logout() {
        prefs.edit().remove("jwt").remove("id").commit()
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

    suspend fun putUser(
        username: String,
        email: String,
        password: String
    ) : AuthResult<User> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val id = prefs.getInt("id", -1)
            val result = api.putUser(
                token = token,
                id = id,
                request = RegisterRequest(
                    username = username,
                    email = email,
                    password = password
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

    suspend fun deleteUser() : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val id = prefs.getInt("id", -1)
            api.deleteUser(token, id)
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

    suspend fun postChallenge(
        title: String,
        description: String,
    ) : AuthResult<Challenge> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val id = prefs.getInt("id", -1)
            val result = api.postChallenge(
                token = token,
                request = ChallRequest(
                    Title = title,
                    Description = description,
                    UserID = id
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

    suspend fun putChallenge(
        challengeid: Int,
        title: String,
        description: String,
    ) : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val id = prefs.getInt("id", -1)
            api.putChallenge(
                token = token,
                id = challengeid,
                request = ChallRequest(
                    Title = title,
                    Description = description,
                    UserID = id
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

    suspend fun deleteChallenge(
        id: Int
    ) : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.deleteChallenge(
                token = token,
                id = id
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

    suspend fun postChallengeExample(
        input: String,
        output: String,
        description: String,
        challengeid: Int
    ) : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.postChallengeExample(
                token = token,
                request = ExamRequest(
                    Ex_input = input,
                    Ex_output = output,
                    Description = description,
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

    suspend fun putChallengeExample(
        input: String,
        output: String,
        description: String,
        challengeid: Int,
        id: Int
    ) : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.putChallengeExample(
                token = token,
                id = id,
                request = ExamRequest(
                    Ex_input = input,
                    Ex_output = output,
                    Description = description,
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

    suspend fun deleteChallengeExample(
        id: Int
    ) : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.deleteChallengeExample(
                token = token,
                id = id
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

    suspend fun postChallengeTarget(
        input: String,
        output: String,
        challengeid: Int
    ) : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.postChallengeTarget(
                token = token,
                request = TargetRequest(
                    Input = input,
                    Target_output = output,
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

    suspend fun putChallengeTarget(
        input: String,
        output: String,
        challengeid: Int,
        id: Int
    ) : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.putChallengeTarget(
                token = token,
                id = id,
                request = TargetRequest(
                    Input = input,
                    Target_output = output,
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

    suspend fun deleteChallengeTarget(
        id: Int
    ) : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.deleteChallengeTarget(
                token = token,
                id = id
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

    suspend fun postChallengeLabel(
        labelid: Int,
        challengeid: Int
    ) : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.postChallengeLabel(
                token = token,
                challengeId = challengeid,
                labelId = labelid
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

    suspend fun deleteChallengeLabel(
        labelid: Int,
        challengeid: Int
    ) : AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.deleteChallengeLabel(
                token = token,
                challengeId = challengeid,
                labelId = labelid
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