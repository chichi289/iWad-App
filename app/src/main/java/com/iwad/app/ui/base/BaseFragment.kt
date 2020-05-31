package com.iwad.app.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.iwad.app.exception.AuthException
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Timber.e("handleOnBackPressed()")
                /*if(onBackPressed()){
                    val childFragmentManager = activity?.supportFragmentManager?.getChildrenFragmentManager()
                    val fragmentCount = childFragmentManager?.backStackEntryCount
                    //val currentFragment: Fragment? = childFragmentManager?.fragments?.first()
                    if(fragmentCount == 0){
                        activity?.finish()
                    }else{
                        findNavController().navigateUp()
                    }

                }*/
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObservers()
        bindData()

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() { // Can be go in onStart()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
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


    // abstract methods
    @LayoutRes
    abstract fun layout(): Int
    abstract fun setObservers()
    abstract fun bindData()

    // concrete methods called from BaseActivity
    open fun onBackPressed(): Boolean {
        return true
    }

}