package com.example.jwt_kotlin.repository

import com.example.jwt_kotlin.entity.City
import com.example.jwt_kotlin.entity.Region
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.awt.print.Pageable

interface RegionRepository : JpaRepository<Region, Int> {

    @Query(
        value = "SELECT * from cities c JOIN regions r on r.id = c.city_id where r.id = ?1",
        nativeQuery = true)
    fun findByIdCities(id: Int): List<Region>

    fun findDistinctByCitiesNotNull(pageable: org.springframework.data.domain.Pageable) : List<Region>

}