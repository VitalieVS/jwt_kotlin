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
}