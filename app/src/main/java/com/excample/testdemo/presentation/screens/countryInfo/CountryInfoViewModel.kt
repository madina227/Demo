package com.excample.testdemo.presentation.screens.countryInfo

import com.excample.testdemo.data.source.remote.dto.response.GetAllResponseItem
import com.excample.testdemo.presentation.viewModels.BaseViewModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Madina Agzamova
 * on 16,January,2023
 **/

interface CountryInfoViewModel:BaseViewModel {
    val countryFlow: Flow<GetAllResponseItem>

}