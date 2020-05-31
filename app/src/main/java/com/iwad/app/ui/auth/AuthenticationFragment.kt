package com.iwad.app.ui.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.iwad.app.R
import com.iwad.app.extentions.toast
import com.iwad.app.ui.base.BaseFragment
import com.iwad.app.utils.text_decorator.TextDecorator
import kotlinx.android.synthetic.main.fragment_authentication.*

class AuthenticationFragment : BaseFragment() {

    override fun layout(): Int = R.layout.fragment_authentication
    override fun setObservers() {}
    override fun bindData() {}

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewWelcomeToIWad.text =
            "${getString(R.string.welcome_to)} ${getString(R.string.app_name)}"

        TextDecorator.decorate(textViewWelcomeToIWad, textViewWelcomeToIWad.text.toString())
            .setTextColor(R.color.colorBlack, getString(R.string.welcome_to))
            .setTextColor(R.color.colorYellow, getString(R.string.app_name))
            .build()

        buttonLogin.setOnClickListener {
            findNavController().navigate(AuthenticationFragmentDirections.actionAuthenticationFragmentToLoginFragment().setTestArgs("Chirag"))
        }

        buttonSignUp.setOnClickListener {
            findNavController().navigate(AuthenticationFragmentDirections.actionAuthenticationFragmentToSignupFragment())
        }
    }
}
