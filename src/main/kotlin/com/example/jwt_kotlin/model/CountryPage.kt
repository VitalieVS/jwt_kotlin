package com.example.jwt_kotlin.model

import lombok.Data
import org.springframework.data.domain.Sort

@Data
data class CountryPage(
    val pageNumber: Int = 0,
    val pageSize: Int = 5,
    val sortDirection: Sort.Direction = Sort.Direction.ASC,
    val sortBy: String = "name"
)
