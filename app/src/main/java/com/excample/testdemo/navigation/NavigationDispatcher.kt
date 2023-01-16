package com.excample.testdemo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor() : Navigator, Handler {
    override val navigationStack = MutableSharedFlow<(NavController) -> Unit>()

    private suspend fun navigator(args: NavController.() -> Unit) {
        navigationStack.emit(args)
    }

    override suspend fun navigateTo(direction: NavDirections) = navigator { navigate(direction) }
    override suspend fun back() = navigator { popBackStack() }

}