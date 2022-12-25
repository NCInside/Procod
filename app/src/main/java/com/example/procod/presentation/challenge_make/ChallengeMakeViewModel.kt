package com.example.procod.presentation.challenge_make

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.procod.data.repository.AppRepository
import com.example.procod.util.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeMakeViewModel @Inject constructor(
    private val repository: AppRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(ChallengeMakeState())
    var id: Int = -1

    init {
        id = savedStateHandle.get<Int>("id") ?: -1
        if (id < 0) postChallenge() else {
            getChallenge(id)
        }
        getLabels()
        getStatisticUser()
    }

    fun onEvent(event: ChallengeMakeEvent) {
        when(event) {
            is ChallengeMakeEvent.OnTitleChange -> {
                state = state.copy(title = event.query)
            }
            is ChallengeMakeEvent.OnDescriptionChange -> {
                state = state.copy(description = event.query)
            }
            is ChallengeMakeEvent.OnExInputChange -> {
                state = state.copy(ex_input = event.query)
            }
            is ChallengeMakeEvent.OnExOutputChange -> {
                state = state.copy(ex_output = event.query)
            }
            is ChallengeMakeEvent.OnExDescriptionChange -> {
                state = state.copy(ex_description = event.query)
            }
            is ChallengeMakeEvent.OnInputChange -> {
                state = state.copy(input = event.query)
            }
            is ChallengeMakeEvent.OnOutputChange -> {
                state = state.copy(output = event.query)
            }
            is ChallengeMakeEvent.AddLabel -> {
                state = state.copy(labelId = event.id)
                postLabel()
            }
            is ChallengeMakeEvent.MinLabel -> {
                state = state.copy(labelId = event.id)
                deleteLabel()
            }
            is ChallengeMakeEvent.SubmitExample -> {
                if (state.exId > 0) {
                    putExample(state.exId)
                    state = state.copy(exId = -1)
                } else postExample()
            }
            is ChallengeMakeEvent.SubmitTarget -> {
                if (state.targetId > 0) {
                    putTarget(state.targetId)
                    state = state.copy(targetId = -1)
                } else postTarget()
            }
            is ChallengeMakeEvent.Submit -> {
                putChallenge()
            }
            is ChallengeMakeEvent.EditExample -> {
                state = state.copy(
                    exId = event.id,
                    ex_output = event.output,
                    ex_input = event.input,
                    ex_description = event.desc
                )
            }
            is ChallengeMakeEvent.EditTarget -> {
                state = state.copy(
                    targetId = event.id,
                    output = event.output,
                    input = event.input
                )
            }
            is ChallengeMakeEvent.CancelExample -> {
                state = state.copy(
                    exId = -1,
                    ex_output = "",
                    ex_input = "",
                    ex_description = ""
                )
            }
            is ChallengeMakeEvent.CancelTarget -> {
                state = state.copy(
                    targetId = -1,
                    output = "",
                    input = ""
                )
            }
            is ChallengeMakeEvent.DeleteExample -> {
                deleteExample(event.id)
            }
            is ChallengeMakeEvent.DeleteTarget -> {
                deleteTarget(event.id)
            }
        }
    }

    fun getLabels() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val labelResult = repository.getLabels()
            when (val result = labelResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(
                        labels = result.data!!
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    fun postChallenge() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val challengeResult = repository.postChallenge(
                title = state.title,
                description = state.description
            )
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

    fun getChallenge(id: Int?) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val challengeResult = repository.getChallenge(id ?: state.challenge?.ID!!)
            when (val result = challengeResult) {
                is AuthResult.Authorized -> {
                    state = state.copy(
                        challenge = result.data,
                    )
                    state = state.copy(
                        title = state.challenge?.Title!!,
                        description = state.challenge?.Description!!,
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    fun putChallenge() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val challengeResult = repository.putChallenge(
                title = state.title,
                challengeid = state.challenge?.ID!!,
                description = state.description
            )
            when (val result = challengeResult) {
                is AuthResult.Authorized -> {
                    postStatistic()
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    fun postExample() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val exampleResult = repository.postChallengeExample(
                input = state.ex_input,
                output = state.ex_output,
                description = state.ex_description,
                challengeid = state.challenge?.ID!!
            )
            when (val result = exampleResult) {
                is AuthResult.Authorized -> {
                    getChallenge(null)
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    fun putExample(id: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val exampleResult = repository.putChallengeExample(
                input = state.ex_input,
                output = state.ex_output,
                description = state.ex_description,
                challengeid = state.challenge?.ID!!,
                id = id
            )
            when (val result = exampleResult) {
                is AuthResult.Authorized -> {
                    getChallenge(null)
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    fun deleteExample(id: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val exampleResult = repository.deleteChallengeExample(
                id = id
            )
            when (val result = exampleResult) {
                is AuthResult.Authorized -> {
                    getChallenge(null)
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    fun postTarget() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val targetResult = repository.postChallengeTarget(
                input = state.input,
                output = state.output,
                challengeid = state.challenge?.ID!!
            )
            when (val result = targetResult) {
                is AuthResult.Authorized -> {
                    getChallenge(null)
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    fun putTarget(id: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val targetResult = repository.putChallengeTarget(
                input = state.input,
                output = state.output,
                challengeid = state.challenge?.ID!!,
                id = id
            )
            when (val result = targetResult) {
                is AuthResult.Authorized -> {
                    getChallenge(null)
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    fun deleteTarget(id: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val targetResult = repository.deleteChallengeTarget(
                id = id
            )
            when (val result = targetResult) {
                is AuthResult.Authorized -> {
                    getChallenge(null)
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    fun postLabel() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val labelResult = repository.postChallengeLabel(
                challengeid = state.challenge?.ID!!,
                labelid = state.labelId
            )
            when (val result = labelResult) {
                is AuthResult.Authorized -> {
                    getChallenge(null)
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    fun deleteLabel() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val labelResult = repository.deleteChallengeLabel(
                challengeid = state.challenge?.ID!!,
                labelid = state.labelId
            )
            when (val result = labelResult) {
                is AuthResult.Authorized -> {
                    getChallenge(null)
                }
            }
            state = state.copy(isLoading = false)
        }
    }

    private fun getStatisticUser() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val statisticResult = repository.getStatisticUser()
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

    private fun postStatistic() {
        viewModelScope.launch {
            repository.postStatistic(
                num_challenge_attempted = state.statistic?.Num_challenge_attempted!!,
                num_challenge_completed = state.statistic?.Num_challenge_completed!!,
                num_challenge_made = state.statistic?.Num_challenge_made!! + 1,
                total_memory = state.statistic?.Total_memory!!,
                total_runtime = state.statistic?.Total_runtime!!
            )
        }
    }

}