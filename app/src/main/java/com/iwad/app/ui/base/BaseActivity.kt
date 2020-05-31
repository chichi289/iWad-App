package com.iwad.app.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.iwad.app.IWadApp
import com.iwad.app.exception.AuthException
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as IWadApp).getApplicationComponent()?.inject(this)

        setContentView(createLayout())
        setObservers()
    }

    abstract fun createLayout(): Int
    abstract fun setObservers()

   open fun onError(exception: Exception) {
        when (exception) {
            is ConnectException -> {
                Timber.e("No Internet:-̥ $exception")
            }
            is SocketTimeoutException -> {
                Timber.e("Time out:- $exception")
            }
            is AuthException -> {
                Timber.e("AuthException:- $exception")
            }
            else -> {
                Timber.e("Something went wrong:- $exception")
            }
        }
    }
}
