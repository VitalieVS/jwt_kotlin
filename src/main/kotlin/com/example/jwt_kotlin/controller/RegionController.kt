package com.example.jwt_kotlin.controller

import com.example.jwt_kotlin.service.region_service.RegionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class RegionController {

    @Autowired
    lateinit var regionService: RegionService

    @GetMapping("/region/{id}")
    fun getRegionsByName(@PathVariable id: Int) = regionService.getFilteredByName(id)
}