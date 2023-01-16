package com.excample.testdemo.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.excample.testdemo.data.source.remote.dto.response.GetAllResponse
import com.excample.testdemo.domain.repository.AppRepository
import com.excample.testdemo.presentation.screens.allCountries.AllCountryViewModel
import com.excample.testdemo.utils.LoadingType
import com.excample.testdemo.utils.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCountryViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : AllCountryViewModel, ViewModel() {
    override val countries: MutableSharedFlow<GetAllResponse> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

    override val failureFlow: MutableSharedFlow<String> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    override val successFlow: MutableSharedFlow<Any> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    override val loading: MutableSharedFlow<LoadingType> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    override val hasConnection: MutableSharedFlow<Boolean> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    override val isValidFlow: MutableSharedFlow<Boolean> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

    override fun getAllCountries() {
        viewModelScope.launch {
            repository.getAllCountries().collect {
                when (it) {
                    is ResultData.Failure -> failureFlow.emit(it.message)
                    is ResultData.HasConnection -> hasConnection.emit(it.state)
                    is ResultData.Loading -> loading.emit(it.state)
                    is ResultData.Success -> countries.emit(it.data!!)
                }
            }
        }
    }

    init {
        getAllCountries()
    }
}