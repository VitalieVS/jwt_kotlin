package com.example.jwt_kotlin.controller

import com.example.jwt_kotlin.dto.CountryRequest
import com.example.jwt_kotlin.entity.Country
import com.example.jwt_kotlin.repository.CountryRepository
import com.example.jwt_kotlin.service.country_service.CountryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CountryController {

    @Autowired
    lateinit var countryService: CountryService

    @Autowired
    lateinit var countryRepository: CountryRepository

//    @PostMapping("/addCountry")
//    fun placeCountry(@RequestBody request: Country): Country {
//        println("MY REQUEST")
//        println(request)
//        return countryService.saveCountry(request)
//    }

    @PostMapping("/placeCountry")
    fun addCountry(@RequestBody request: CountryRequest): Country? {
        return request.country?.let { countryService.saveCountry(it) }
    }


    @GetMapping("/country/name/{name}")
    fun getCountryByName(@PathVariable name: String): Country {
        return countryService.showCountryName(name)
    }

    @GetMapping("/countries")
    fun getCountries(): MutableList<Country> {
        return countryService.getCountries()
    }
}