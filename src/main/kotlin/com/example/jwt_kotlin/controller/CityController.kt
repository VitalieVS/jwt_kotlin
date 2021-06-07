package com.example.jwt_kotlin.controller

import com.example.jwt_kotlin.model.CountryPage
import com.example.jwt_kotlin.service.city_service.CityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class CityController {
    @Autowired
    lateinit var cityService: CityService

    @GetMapping
    @RequestMapping(value = ["/pagedcities", "/pagedcities/{id}"])
    fun getPagedCities(@PathVariable(required = false) id: Int?, countryPage: CountryPage?): Any? {
        if (id == null) return countryPage?.let { cityService.getPagedFiltered(it) }

        return countryPage?.let {cityService.getCitiesByRegionId(it, id)}

        //cityService.getRegionsById(id)
    }
}
