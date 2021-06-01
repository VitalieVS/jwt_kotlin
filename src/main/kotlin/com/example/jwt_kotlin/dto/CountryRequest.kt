package com.example.jwt_kotlin.dto

import com.example.jwt_kotlin.entity.Country
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
data class CountryRequest(
    var country: Country? = null
)
