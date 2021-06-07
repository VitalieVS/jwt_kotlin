package com.example.jwt_kotlin.controller

import com.example.jwt_kotlin.dto.CountryRequest
import com.example.jwt_kotlin.entity.Country
import com.example.jwt_kotlin.model.CountryPage
import com.example.jwt_kotlin.service.country_service.CountryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CountryController {

    @Autowired
    lateinit var countryService: CountryService

    @PostMapping("/country/addCountry")
    fun addCountry(@RequestBody request: CountryRequest): Country? =
        request.country?.let { countryService.saveCountry(it) }

    @GetMapping("/country/name/{name}")
    fun getCountryByName(@PathVariable name: String): Country = countryService.showCountryName(name)

    @GetMapping("/country/countries")
    fun getCountries(): MutableList<Country> = countryService.getCountries()

    @PutMapping("/country/update")
    fun updateCountry(@RequestBody request: CountryRequest): Country? =
        request.country?.let { countryService.updateCountry(it) }

    @DeleteMapping("/country/delete/{id}")
    fun deleteCountry(@PathVariable id: Int): String = countryService.removeById(id)

    @GetMapping("/country/pagedcountries")
    fun getPagedCountries(countryPage: CountryPage?): Any? =
        ResponseEntity<Any?>(countryPage?.let {
            countryService.getPagedCountries(it)
        }, HttpStatus.OK)

}