package com.excample.testdemo.presentation.viewModels

import com.excample.testdemo.utils.LoadingType
import kotlinx.coroutines.flow.MutableSharedFlow


interface BaseViewModel {
    val failureFlow: MutableSharedFlow<String>
    val successFlow: MutableSharedFlow<Any>
    val loading: MutableSharedFlow<LoadingType>
    val hasConnection: MutableSharedFlow<Boolean>
    val isValidFlow: MutableSharedFlow<Boolean>
}