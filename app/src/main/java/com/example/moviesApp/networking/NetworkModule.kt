package com.example.moviesApp.networking

import android.annotation.SuppressLint
import com.aqarmap.lib.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun retrofitService(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NetworkConfig.baseUrl)
            .build()
    }

    @SuppressLint("SuspiciousIndentation")
    @Provides
    @Singleton
    fun movieOkHttpClient(
    ): OkHttpClient {
        val okHttpClient = OkHttpClient
            .Builder()
            .callTimeout(NetworkConfig.callTimeout, TimeUnit.SECONDS)
            .readTimeout(NetworkConfig.readTimeout, TimeUnit.SECONDS)
            .writeTimeout(NetworkConfig.writeTimeout, TimeUnit.SECONDS)

        okHttpClient
            .addInterceptor(CurlLoggingInterceptor())
            .addInterceptor(ResponseLoggingInterceptor())

        return okHttpClient.build()
    }
}