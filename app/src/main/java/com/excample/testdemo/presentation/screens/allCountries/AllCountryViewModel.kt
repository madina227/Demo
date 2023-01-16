package com.excample.testdemo.presentation.screens.allCountries

import com.excample.testdemo.data.source.remote.dto.response.GetAllResponse
import com.excample.testdemo.presentation.viewModels.BaseViewModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Madina Agzamova
 * on 16,January,2023
 **/

interface AllCountryViewModel : BaseViewModel {
    val countries: Flow<GetAllResponse>

    fun getAllCountries()
}