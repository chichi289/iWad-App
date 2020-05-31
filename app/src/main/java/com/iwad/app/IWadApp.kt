package com.iwad.app

import android.app.Application
import com.iwad.app.di.componant.ApplicationComponent
import com.iwad.app.di.componant.DaggerApplicationComponent
import com.iwad.app.di.module.ApplicationModule
import timber.log.Timber
import javax.inject.Inject

class IWadApp:Application() {
    private lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var debugTree: Timber.DebugTree

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        applicationComponent.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(debugTree)
        }
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return if (::applicationComponent.isInitialized) {
            applicationComponent
        } else {
            null
        }
    }
}