package com.excample.testdemo.data.repositoryImpl

import android.util.Log
import com.excample.testdemo.data.source.remote.dto.response.GetAllResponse
import com.excample.testdemo.data.source.remote.dto.response.GetAllResponseItem
import com.excample.testdemo.data.source.remote.dto.response.GetCountry
import com.excample.testdemo.data.source.remote.service.CountryApi
import com.excample.testdemo.domain.repository.AppRepository
import com.excample.testdemo.utils.ConnectionUtil
import com.excample.testdemo.utils.LoadingType
import com.excample.testdemo.utils.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Madina Agzamova
 * on 16,January,2023
 **/

class AppRepositoryImpl @Inject constructor(
    private val countryApi: CountryApi,
    private val connectionUtil: ConnectionUtil
) : AppRepository {
    override fun getAllCountries(): Flow<ResultData<GetAllResponse>> =
        flow<ResultData<GetAllResponse>> {
            if (connectionUtil.hasConnection()) {
                emit(ResultData.HasConnection(true))
                emit(ResultData.Loading(LoadingType(fullScreen = true)))
                val response = countryApi.getAll()
                if (response.isSuccessful) {
                    response.body()?.let {
                        val message = "Get all countries successfully"
                        emit(ResultData.Success(it))
                        emit(ResultData.Loading(LoadingType(fullScreen = false)))
                    }
                } else emit(ResultData.Failure(response.message()))
            } else {
                emit(ResultData.HasConnection(false))
            }
        }.catch { error ->
            error.message?.let { message -> emit(ResultData.Failure(message)) }
        }.flowOn(Dispatchers.IO)

//    override fun getCountry(countryName: String): Flow<ResultData<GetAllResponseItem>> =
//        flow<ResultData<GetAllResponseItem>> {
//            if (connectionUtil.hasConnection()) {
//                emit(ResultData.HasConnection(true))
//                emit(ResultData.Loading(LoadingType(fullScreen = true)))
//                val response = countryApi.getCountry(countryName)
//
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        val message = "Get country successfully"
//                        emit(ResultData.Success(it))
//                        emit(ResultData.Loading(LoadingType(fullScreen = false)))
//                    }
//                } else emit(ResultData.Failure(response.message()))
//            } else {
//                emit(ResultData.HasConnection(false))
//            }
//        }.catch { error ->
//            error.message?.let { message -> emit(ResultData.Failure(message)) }
//        }.flowOn(Dispatchers.IO)
}