package com.iwad.app.ui.auth

import android.util.Patterns
import androidx.navigation.fragment.findNavController
import com.iwad.app.R
import com.iwad.app.extentions.toast
import com.iwad.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_forgot_password.*

class ForgotPasswordFragment : BaseFragment() {
    override fun layout(): Int = R.layout.fragment_forgot_password
    override fun setObservers() {}
    override fun bindData() {
        buttonSend.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            if (email.isEmpty()) {
                context?.toast(getString(R.string.please_enter_email))
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                context?.toast(getString(R.string.please_enter_valid_email))
            }else{
                context?.toast(getString(R.string.please_check_your_email_to_reset_password))
                activity?.onBackPressed()
            }
        }
    }
}
