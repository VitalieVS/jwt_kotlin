package com.example.jwt_kotlin.controller

import com.example.jwt_kotlin.entity.City
import com.example.jwt_kotlin.entity.Region
import com.example.jwt_kotlin.model.CountryPage
import com.example.jwt_kotlin.repository.RegionRepository
import com.example.jwt_kotlin.service.region_service.RegionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class RegionController {

    @Autowired
    lateinit var regionService: RegionService

    @GetMapping("/region/{id}")
    fun getRegionsByName(@PathVariable id: Int) = regionService.getRegionsById(id)

    @GetMapping
    @RequestMapping(value = ["/pagedregions", "/pagedregions/{id}"])
    fun getPagedCities(@PathVariable(required = false) id: Int?, countryPage: CountryPage?): Any? {
        if (id == null) return countryPage?.let { regionService.getPagedFiltered(it) }

        return regionService.getRegionsById(id)
    }
}
