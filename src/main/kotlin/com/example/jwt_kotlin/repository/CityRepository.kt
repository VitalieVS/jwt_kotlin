package com.example.jwt_kotlin.repository

import com.example.jwt_kotlin.entity.City
import com.example.jwt_kotlin.entity.Region
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CityRepository : JpaRepository<City, Int> {
    @Query(
        value = "SELECT * from cities c JOIN regions r on r.id = c.city_id where r.id = :ids",
        nativeQuery = true
    )
    fun findByIdCities(pageable: Pageable, @Param("ids") ids: Int): Page<City>

    fun findByName(name: String): City
}