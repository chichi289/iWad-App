package com.iwad.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

open class BaseViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val scope = viewModelScope + viewModelJob
    fun withCoroutine(call: suspend (() -> Unit)) =
        scope.launch {
            call()
        }

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}