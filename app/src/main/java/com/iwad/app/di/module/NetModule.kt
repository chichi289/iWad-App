package com.iwad.app.di.module

import com.iwad.app.IWadApp
import com.iwad.app.api.URLFactory
import com.iwad.app.api.core.NoConnectionInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {

    @Singleton
    @Provides
    @Named("header_interceptor")
    fun provideInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest = chain.request()
                    .newBuilder()
                    .addHeader("User-Agent", "Retrofit-Sample-App")
                    .build()
                return chain.proceed(newRequest)
            }
        }
    }

    @Singleton
    @Provides
    @Named("logging_interceptor")
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    @Named("connection_interceptor")
    fun provideConnectionInterceptors(context: IWadApp): NoConnectionInterceptor {
        return NoConnectionInterceptor(context)
    }


    @Singleton
    @Provides
    fun provideOkhttp(
        @Named("header_interceptor") interceptor: Interceptor,
        @Named("logging_interceptor") httpLoggingInterceptor : HttpLoggingInterceptor,
        @Named("connection_interceptor") noConnectionInterceptor : NoConnectionInterceptor): OkHttpClient {
        val okhttpBuilder = OkHttpClient.Builder()
        okhttpBuilder.addInterceptor(interceptor)
        okhttpBuilder.addInterceptor(httpLoggingInterceptor)
        okhttpBuilder.addInterceptor(noConnectionInterceptor)
        return okhttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URLFactory.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}