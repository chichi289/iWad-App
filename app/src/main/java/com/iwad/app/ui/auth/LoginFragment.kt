package com.iwad.app.ui.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Patterns
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.iwad.app.R
import com.iwad.app.extentions.toast
import com.iwad.app.ui.activity.HomeActivity
import com.iwad.app.ui.base.BaseFragment
import com.iwad.app.utils.text_decorator.OnTextClickListener
import com.iwad.app.utils.text_decorator.TextDecorator
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber

class LoginFragment : BaseFragment(), View.OnClickListener {

    private val safeArgs by navArgs<LoginFragmentArgs>()

    override fun layout(): Int = R.layout.fragment_login

    override fun setObservers() {}

    @SuppressLint("SetTextI18n")
    override fun bindData() {

        Timber.e("Safe arg received ${safeArgs.userEmail}")

        textViewNewUserSignUp.text = "${getString(R.string.new_user)} ${getString(R.string.signup)}"

        TextDecorator.decorate(textViewNewUserSignUp, textViewNewUserSignUp.text.toString())
            .makeTextClickable(OnTextClickListener { _, _ ->
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment())
            }, true, getString(R.string.signup))
            .setTextColor(R.color.colorYellow, getString(R.string.signup))
            .build()

        textViewForgotPassword.setOnClickListener(this)
        buttonLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.textViewForgotPassword -> findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
            R.id.buttonLogin -> {
                val email = editTextEmail.text.toString().trim()
                val password = editTextPassword.text.toString().trim()
                if (email.isEmpty()) {
                    context?.toast(getString(R.string.please_enter_email))
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    context?.toast(getString(R.string.please_enter_valid_email))
                } else if (password.isEmpty()) {
                    context?.toast(getString(R.string.please_enter_password))
                }else{
                    activity?.let {
                        val intent = Intent(it, HomeActivity::class.java)
                        intent.putExtra("email",email)
                        startActivity(intent)
                        it.finish()
                    }
                }

            }
        }
    }
}
