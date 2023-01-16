package com.excample.testdemo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.excample.testdemo.navigation.Handler
import com.excample.testdemo.navigation.NavigationDispatcher
import com.excample.testdemo.navigation.Navigator

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindsNavigator(impl: NavigationDispatcher): Navigator

    @Binds
    fun bindsHandler(impl: NavigationDispatcher): Handler

}