package com.iwad.app.api.core

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AppLiveData<T>(function: suspend () -> T) {

    private val handler = CoroutineExceptionHandler { _, exception ->
        GlobalScope.launch(Dispatchers.Main) { error?.invoke(exception) }
    }

    private var error: ((throwable: Throwable) -> Unit)? = null
    private val liveData: LiveData<T>

    init {

        liveData = androidx.lifecycle.liveData(Dispatchers.IO + handler) {
            emit(function())
        }
    }

    fun liveData(): LiveData<T> = liveData

    fun setOnError(error: (throwable: Throwable) -> Unit): AppLiveData<T> {
        this.error = error
        return this
    }

}