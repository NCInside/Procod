package com.example.procod.presentation.challenge_work

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.procod.data.repository.AppRepository
import com.example.procod.data.repository.JudgeRepository
import com.example.procod.util.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeWorkViewModel @Inject constructor(
    private val judgeRepository: JudgeRepository,
    private val appRepository: AppRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(ChallengeWorkState())

    init {
        getChallenge()
    }

    fun onEvent(event: ChallengeWorkEvent) {
        when(event) {
            is ChallengeWorkEvent.Reset -> {
                state = state.copy(code = "")
            }
            is ChallengeWorkEvent.Submit -> {
                runCode(state.code, state.challenge?.ChallengeTargets?.get(0)!!.Input!!)
            }
            is ChallengeWorkEvent.OnCodeChange -> {
                state = state.copy(code = event.query)
            }
        }
    }

    fun getChallenge() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val id = savedStateHandle.get<Int>("id") ?: return@launch
            val challengeResult = appRepository.getChallenge(id)
            when (val result = challengeResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(
                        challenge = result.data
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    private fun runCode(code: String, input: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val tokenResult = judgeRepository.submitCode(code = code, stdin = input)
            when (val result = tokenResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(token = result.data!!.token)
                    coroutineScope {
                        getStatisticUser()
                        while (state.result == null || state.result?.status?.id!! < 3) {
                            getResult(state.token)
                            delay(100)
                        }

                    }
                }
                is AuthResult.Unauthorized -> {
                    state = state.copy(token = "error1")
                }
                is AuthResult.UnknownError -> {
                    state = state.copy(token = "error2")
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    private fun getResult(token: String) {
        viewModelScope.launch {
            val codeResult = judgeRepository.getResult(token)
            when (val result = codeResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(result = result.data)
                    if (state.result?.status?.id!! >= 3) {
                        postSubmission(
                            code = state.code,
                            runtime = (state.result?.time!!.toDouble() * 1000).toInt(),
                            is_correct = state.result?.stdout == state.challenge?.ChallengeTargets?.get(0)!!.Target_output!!,
                            error = state.result?.stderr!!,
                            memory = state.result?.memory!!,
                            challengeid = savedStateHandle.get<Int>("id")!!
                        )
                        postStatistic(
                            num_challenge_made = state.statistic?.Num_challenge_made!!,
                            num_challenge_completed = state.statistic?.Num_challenge_completed!! + if (state.result?.status?.id!! == 3) 1 else 0,
                            num_challenge_attempted = state.statistic?.Num_challenge_completed!! + 1,
                            total_runtime = state.statistic?.Total_runtime!! + (state.result?.time!!.toDouble() * 1000).toInt(),
                            total_memory = state.statistic?.Total_memory!! + state.result?.memory!!
                        )
                    }
                }
            }
        }
    }

    private fun postSubmission(
        code: String,
        runtime: Int,
        is_correct: Boolean,
        error: String,
        memory: Int,
        challengeid: Int
    ) {
        viewModelScope.launch {
            val submitResult = appRepository.postSubmission(
                code = code,
                runtime = runtime,
                is_correct = is_correct,
                error = error,
                memory = memory,
                challengeid = challengeid
            )
        }
    }

    private fun getStatisticUser() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val statisticResult = appRepository.getStatisticUser()
            when (val result = statisticResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(
                        statistic = result.data
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    private fun postStatistic(
        num_challenge_attempted : Int,
        num_challenge_completed : Int,
        num_challenge_made : Int,
        total_memory : Int,
        total_runtime : Int
    ) {
        viewModelScope.launch {
            appRepository.postStatistic(
                num_challenge_attempted = num_challenge_attempted,
                num_challenge_completed = num_challenge_completed,
                num_challenge_made = num_challenge_made,
                total_memory = total_memory,
                total_runtime = total_runtime
            )
        }
    }

}