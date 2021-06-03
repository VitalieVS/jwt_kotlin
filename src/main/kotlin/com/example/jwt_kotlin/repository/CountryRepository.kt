package com.example.jwt_kotlin.repository

import com.example.jwt_kotlin.entity.Country
import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository : JpaRepository<Country, Int> {
    fun findByName(name: String): Country;
    fun save(country: Country?): Country
}