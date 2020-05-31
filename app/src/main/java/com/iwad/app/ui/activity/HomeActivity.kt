package com.iwad.app.ui.activity

import com.iwad.app.R
import com.iwad.app.ui.base.BaseActivity

class HomeActivity : BaseActivity() {
    override fun createLayout(): Int = R.layout.activity_home
    override fun navHostFragmentId(): Int = R.id.nav_host_fragment_container
    override fun setObservers() {}
}
