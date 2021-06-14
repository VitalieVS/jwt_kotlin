package com.example.jwt_kotlin.dto

import com.example.jwt_kotlin.entity.Region

data class CountrySearchCriteria (
    var id: Int? = null,
    var name: String? = null,
    var peopleCount: Long = 0,
 //   var regions: List<Region>? = null,
    var ids: List<Int>? = null,
    var regionName: String? = null
    )