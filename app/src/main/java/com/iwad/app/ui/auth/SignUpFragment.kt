package com.iwad.app.ui.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Patterns
import androidx.navigation.fragment.findNavController
import com.iwad.app.R
import com.iwad.app.extentions.toast
import com.iwad.app.ui.activity.HomeActivity
import com.iwad.app.ui.base.BaseFragment
import com.iwad.app.utils.text_decorator.OnTextClickListener
import com.iwad.app.utils.text_decorator.TextDecorator
import kotlinx.android.synthetic.main.fragment_signup.*
import timber.log.Timber

class SignUpFragment : BaseFragment() {

    override fun layout(): Int = R.layout.fragment_signup

    override fun setObservers() {}

    @SuppressLint("SetTextI18n")
    override fun bindData() {
        textViewAlreadyAccount.text =
            "${getString(R.string.you_already_have_an_account)} ${getString(R.string.login)}"

        TextDecorator.decorate(textViewAlreadyAccount, textViewAlreadyAccount.text.toString())
            .makeTextClickable(OnTextClickListener { _, _ ->
                findNavController().navigate(SignUpFragmentDirections.actionSignupFragmentToLoginFragment())
            }, true, getString(R.string.login))
            .setTextColor(R.color.colorYellow, getString(R.string.login))
            .build()

        buttonSignUp.setOnClickListener {
            val firstName = editTextFirstName.text.toString().trim()
            val lastName = editTextLastName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val phone = editTextPhoneNumber.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val confirmPassword = editTextConfirmPassword.text.toString().trim()

            if (firstName.isEmpty()) {
                context?.toast(getString(R.string.please_enter_first_name))
            } else if (lastName.isEmpty()) {
                context?.toast(getString(R.string.please_enter_last_name))
            } else if (email.isEmpty()) {
                context?.toast(getString(R.string.please_enter_email))
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                context?.toast(getString(R.string.please_enter_valid_email))
            } else if(phone.isEmpty()){
                context?.toast(getString(R.string.please_enter_phone_number))
            } else if(phone.length <8){
                context?.toast(getString(R.string.please_enter_valid_phone_number))
            } else if(password.isEmpty()){
                context?.toast(getString(R.string.please_enter_password))
            } else if(confirmPassword.isEmpty()){
                context?.toast(getString(R.string.please_enter_confirm_password))
            } else if(password != confirmPassword){
                context?.toast(getString(R.string.confirm_password_does_not_match))
            }else{
                activity?.let {
                    startActivity(Intent(it, HomeActivity::class.java))
                    it.finish()
                }
            }
        }
    }

    override fun onBackPressed(): Boolean {
        Timber.e("onBackPressed")
        return super.onBackPressed()
    }
}
