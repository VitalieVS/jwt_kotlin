package com.example.jwt_kotlin.model

import org.springframework.data.domain.Sort

data class CityPage(
    val pageNumber: Int = 0,
    val pageSize: Int = 1,
    val sortDirection: Sort.Direction = Sort.Direction.ASC,
    val sortBy: String = "name"
)