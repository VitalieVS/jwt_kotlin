package com.example.jwt_kotlin.dto

import com.example.jwt_kotlin.entity.City
import com.example.jwt_kotlin.entity.Region

data class RegionsRequest(
    var region: Region,
    var cities: List<City>
)
