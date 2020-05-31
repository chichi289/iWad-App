package com.iwad.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel(){

    private val viewModelJob = SupervisorJob()
    //private val scope = CoroutineScope(Dispatchers.Default + viewModelJob)
    private val scope = viewModelScope + viewModelJob

    fun withCoroutine(call: suspend (() -> Unit)) =
        scope.launch {
           call()
       }


    /*fun <T> withLiveData(liveData: APILiveData<T>,
        callServiceFun: suspend () -> ResponseBody<T>) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            liveData.postValue(DataWrapper(null, throwable))
        }) {
            val response = callServiceFun()
            liveData.postValue(DataWrapper(response, null))
        }
    }*/

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }

}