package com.example.jwt_kotlin.controller

import com.example.jwt_kotlin.dto.CountryRequest
import com.example.jwt_kotlin.dto.CountrySearchCriteria
import com.example.jwt_kotlin.entity.Country
import com.example.jwt_kotlin.model.CountryPage
import com.example.jwt_kotlin.service.country_service.CountryService
import org.springframework.beans.factory.annotation.Autowired
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

    @PutMapping("/country/update")
    fun updateCountry(@RequestBody request: CountryRequest): Country? =
        request.country?.let { countryService.updateCountry(it) }

    @DeleteMapping("/country/delete/{id}")
    fun deleteCountry(@PathVariable id: Int): String = countryService.removeById(id)

    @GetMapping
    @RequestMapping(value = ["/country/pagedcountries", "/country/pagedcountries/{ids}"])
    fun getPagedCountries(@PathVariable(required = false) ids: List<Int>?, countryPage: CountryPage?,
                          countrySearchCriteria: CountrySearchCriteria): Any? {
        return if (ids == null) {
            countryPage?.let { countryService.getFilteredCountries(it, countrySearchCriteria) }
        } else {
            countryPage?.let { countryService.getCitiesId(it, ids) }
        }
    }
}