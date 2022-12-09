package com.example.procod.presentation.sandbox_tab

import androidx.lifecycle.ViewModel
import com.example.procod.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SandboxTabViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {



}