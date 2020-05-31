package com.iwad.app.di.module

import com.iwad.app.api.datasource.AuthDataSource
import com.iwad.app.api.repository.AuthRepository
import com.iwad.app.api.service.AuthService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService {
       return retrofit.create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(authDataSource: AuthDataSource) : AuthRepository = authDataSource


}