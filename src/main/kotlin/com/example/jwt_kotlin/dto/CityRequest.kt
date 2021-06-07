package com.example.jwt_kotlin.dto

import com.example.jwt_kotlin.entity.City
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
data class CityRequest(
    var city: City? = null
)