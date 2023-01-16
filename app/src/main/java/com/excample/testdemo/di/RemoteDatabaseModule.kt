package com.excample.testdemo.di

import android.content.Context
import com.excample.testdemo.data.source.remote.service.CountryApi
import com.excample.testdemo.utils.BASE_URL
import com.mocklets.pluto.Pluto
import com.mocklets.pluto.PlutoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Madina Agzamova
 * on 16,January,2023
 **/
@Module
@InstallIn(SingletonComponent::class)
class RemoteDatabaseModule {

    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun client(
        @ApplicationContext context: Context,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        Pluto.initialize(context)
        return OkHttpClient.Builder().addInterceptor {
            val request = it
                .request()
                .newBuilder()
                .build()
            it.proceed(request)
        }
            .addInterceptor(loggingInterceptor)
            .addInterceptor(PlutoInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    fun countryApi(retrofit: Retrofit): CountryApi =
        retrofit.create(CountryApi::class.java)
}