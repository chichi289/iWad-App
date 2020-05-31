package com.iwad.app.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.iwad.app.R
import com.iwad.app.ui.base.BaseActivity
import com.iwad.app.utils.text_decorator.TextDecorator
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textViewWelcomeToIWad.text =
            "${getString(R.string.welcome_to)} ${getString(R.string.app_name)}"

        TextDecorator.decorate(textViewWelcomeToIWad, textViewWelcomeToIWad.text.toString())
            .setTextColor(R.color.colorBlack, getString(R.string.welcome_to))
            .setTextColor(R.color.colorYellow, getString(R.string.app_name))
            .build()

        Handler().postDelayed({
            startActivity(Intent(this, AuthenticationActivity::class.java))
            finish()
        }, 3000)
    }

    override fun createLayout(): Int = R.layout.activity_splash
    override fun navHostFragmentId(): Int = 0
    override fun setObservers() {}
}
