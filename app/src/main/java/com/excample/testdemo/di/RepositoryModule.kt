package com.excample.testdemo.di

import com.excample.testdemo.data.repositoryImpl.AppRepositoryImpl
import com.excample.testdemo.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Madina Agzamova
 * on 16,January,2023
 **/


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun appRepositoryBind(impl: AppRepositoryImpl): AppRepository
}