package com.example.jwt_kotlin.repository

import com.example.jwt_kotlin.entity.Country
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CountryRepository : JpaRepository<Country, Int> {
    fun findByName(name: String): Country
    fun save(country: Country?): Country

    @Query(
        value = "SELECT * from countries c JOIN regions r on c.id = r.country_id JOIN cities city on city.city_id = r.id where r.id in :ids",
        nativeQuery = true
    )
    fun fetchCities(pageable: Pageable, @Param("ids") ids: List<Int>): Page<Country>

}