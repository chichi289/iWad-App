package com.iwad.app.ui.activity

import com.iwad.app.R
import com.iwad.app.ui.base.BaseActivity

class AuthenticationActivity : BaseActivity() {
    override fun createLayout(): Int = R.layout.activity_authentication
    override fun navHostFragmentId(): Int = R.id.nav_host_fragment_container
    override fun setObservers() {}
}