package com.iwad.app.di.componant

import com.iwad.app.di.module.ApplicationModule
import com.iwad.app.di.module.NetModule
import com.iwad.app.di.module.ServiceModule
import com.iwad.app.ui.activity.MainActivity
import com.iwad.app.ui.base.BaseActivity
import com.iwad.app.IWadApp
import com.iwad.app.di.module.ViewModelModule
import com.iwad.app.ui.home.fragment.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetModule::class, ServiceModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(app: IWadApp)
    fun inject(app: MainActivity)
    fun inject(baseActivity: BaseActivity)
    fun inject(homeFragment: HomeFragment)

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        fun applicationModule(applicationModule: ApplicationModule): Builder
    }
}
