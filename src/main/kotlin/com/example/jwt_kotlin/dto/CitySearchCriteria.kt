package com.example.jwt_kotlin.dto


data class CitySearchCriteria(
    var id: Int? = null,
    var name: String? = null,
    var ids: List<Int>? = null
)
