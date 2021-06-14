package com.example.jwt_kotlin.controller

import com.example.jwt_kotlin.dto.RegionRequest
import com.example.jwt_kotlin.entity.Region
import com.example.jwt_kotlin.model.CityPage
import com.example.jwt_kotlin.service.region_service.RegionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageImpl
import org.springframework.web.bind.annotation.*

@RestController
class RegionController {
    @Autowired
    lateinit var regionService: RegionService

    @GetMapping("/region/name/{name}")
    fun getCityByName(@PathVariable name: String) = regionService.showRegionName(name)

    @PostMapping("/region/addregion")
    fun addCountry(@RequestBody request: RegionRequest): Region? = request.region?.let { regionService.saveRegion(it) }

//    @GetMapping("/region/regions")
//    fun getCities(): MutableList<Region> = regionService.getRegions()

    @PutMapping("/region/update")
    fun updateCity(@RequestBody request: RegionRequest) =
        request.region?.let { regionService.updateRegion(it) }

    @DeleteMapping("/region/delete/{id}")
    fun deleteCity(@PathVariable id: Int) = regionService.removeRegionById(id)
}