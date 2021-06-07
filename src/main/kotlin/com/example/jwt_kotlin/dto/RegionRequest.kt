package com.example.jwt_kotlin.dto

import com.example.jwt_kotlin.entity.Region
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
data class RegionRequest(
    var region: Region? = null
)