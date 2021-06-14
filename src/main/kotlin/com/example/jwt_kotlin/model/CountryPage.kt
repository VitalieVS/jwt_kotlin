package com.example.jwt_kotlin.model

import org.springframework.data.domain.Sort

data class CountryPage(
    val pageNumber: Int = 0,
    val pageSize: Int = 15,
    val sortDirection: Sort.Direction = Sort.Direction.ASC,
    val sortBy: String = "name"
)
