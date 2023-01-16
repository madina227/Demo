package com.excample.testdemo.data.source.remote.service

import com.excample.testdemo.data.source.remote.dto.response.GetAllResponse
import com.excample.testdemo.data.source.remote.dto.response.GetCountry
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Madina Agzamova
 * on 16,January,2023
 **/

interface CountryApi {

    @GET("all")
    suspend fun getAll(): Response<GetAllResponse>

    @GET("name/{name}")
    suspend fun getCountry(@Path("name") name: String): Response<GetCountry>

}