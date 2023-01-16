package com.excample.testdemo.data.source.remote.dto.response

data class RegionalBloc(
    val acronym: String?,
    val name: String?,
    val otherAcronyms: List<String>?,
    val otherNames: List<String>?
)