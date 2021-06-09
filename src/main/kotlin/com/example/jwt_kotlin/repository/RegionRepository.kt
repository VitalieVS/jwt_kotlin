package com.example.jwt_kotlin.repository

import com.example.jwt_kotlin.entity.Region
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository


interface RegionRepository : JpaRepository<Region, Int> {
    fun findDistinctByCitiesNotNull(pageable: org.springframework.data.domain.Pageable): Page<Region>
    fun findByName(name: String): Region
}