package com.excample.testdemo.data.source.remote.dto.response

import java.io.Serializable

data class GetAllResponseItem(
    val alpha2Code: String?,
    val alpha3Code: String?,
    val altSpellings: List<String>?,
    val area: Double?,
    val borders: List<String>?,
    val callingCodes: List<String>?,
    val capital: String?,
    val cioc: String?,
    val currencies: List<Currency>?,
    val demonym: String?,
    val flag: String?,
    val flags: Flags?,
    val gini: Double?,
    val independent: Boolean?,
    val languages: List<Language>?,
    val latlng: List<Double>?,
    val name: String?,
    val nativeName: String?,
    val numericCode: String?,
    val population: Int?,
    val region: String?,
    val regionalBlocs: List<RegionalBloc>?,
    val subregion: String?,
    val timezones: List<String>?,
    val topLevelDomain: List<String>?,
    val translations: Translations?
):Serializable
