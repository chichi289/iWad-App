package com.iwad.app.di.module

import com.iwad.app.IWadApp
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton

@Module
class ApplicationModule(private val app:IWadApp) {

    @Singleton
    @Provides
    fun provideContext():IWadApp=app

    @Singleton
    @Provides
    fun provideDebugTree(): Timber.DebugTree {
        return Timber.DebugTree()
    }
}