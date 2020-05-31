package com.iwad.app.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.iwad.app.IWadApp
import com.iwad.app.exception.AuthException
import com.iwad.app.extentions.getChildrenFragmentManager
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

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() { // Can be go in onResume()
        super.onPause()
    }

    override fun onStop() { // Can be go in onRestart() then onStart()
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun createLayout(): Int
    abstract fun navHostFragmentId(): Int
    abstract fun setObservers()

    /**
     * You can handle back pressed here or
     * inside BaseFragment's onCreate()--> handleOnBackPressed()--> onBackPressedDispatcher()
     *
     * Note: To Handle in BaseFragment. Please comment below onBackPressed() and uncomment
     * handleOnBackPressed() method's implementation in BaseFragment
     * */

    override fun onBackPressed() {
        Timber.e("onBackPressed()")
        val childFragmentManager = supportFragmentManager.getChildrenFragmentManager()
        val fragmentCount = childFragmentManager?.backStackEntryCount
        val currentFragment: Fragment? = childFragmentManager?.fragments?.first()
        currentFragment?.let {
            val fragment = it as BaseFragment
            if (fragment.onBackPressed()) {
                if (fragmentCount == 0) {
                    finish()
                } else {
                    findNavController(this, navHostFragmentId()).navigateUp()
                }
            }
        } ?: super.onBackPressed()
    }

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
