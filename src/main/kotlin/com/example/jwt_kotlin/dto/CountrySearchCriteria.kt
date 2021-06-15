package com.example.jwt_kotlin.dto

data class CountrySearchCriteria (
    var id: Int? = null,
    var name: String? = null,
    var peopleCount: Long = 0,
    var ids: List<Int>? = null,
    var regionName: String? = null
    )