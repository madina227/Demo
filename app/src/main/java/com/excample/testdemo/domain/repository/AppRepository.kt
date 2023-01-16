package com.excample.testdemo.domain.repository

import com.excample.testdemo.data.source.remote.dto.response.GetAllResponse
import com.excample.testdemo.data.source.remote.dto.response.GetAllResponseItem
import com.excample.testdemo.data.source.remote.dto.response.GetCountry
import com.excample.testdemo.utils.ResultData
import kotlinx.coroutines.flow.Flow

/**
 * Created by Madina Agzamova
 * on 16,January,2023
 **/

interface AppRepository {
    fun getAllCountries(): Flow<ResultData<GetAllResponse>>
//    fun getCountry(countryName: String): Flow<ResultData<GetAllResponseItem>>
}