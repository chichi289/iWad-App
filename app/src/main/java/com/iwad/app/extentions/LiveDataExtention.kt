package com.iwad.app.extentions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.iwad.app.exception.AuthException
import com.iwad.app.api.model.response_parser.Result
import com.iwad.app.ui.base.BaseActivity

fun <T> LiveData<Result<T>>.observeOnce(baseActivity: BaseActivity, observer: ResultCallback<T>) {
    observe(baseActivity, Observer<Result<T>> { t ->
        when(t){
            is Result.Success -> observer.onSuccess(t.data)
            is Result.Error -> {
                observer.onError(t.exception)
                baseActivity.onError(t.exception)
            }
            is Result.Unauthorized -> baseActivity.onError(AuthException("Authentication exception"))

        }
    })
}

interface ResultCallback<T>{
    fun  onSuccess(data:T)
    fun  onError(exception:Exception)
}