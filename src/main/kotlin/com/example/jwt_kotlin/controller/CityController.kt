package com.example.jwt_kotlin.controller

import com.example.jwt_kotlin.dto.CityRequest
import com.example.jwt_kotlin.entity.City
import com.example.jwt_kotlin.model.CityPage
import com.example.jwt_kotlin.service.city_service.CityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageImpl
import org.springframework.web.bind.annotation.*


@RestController
class CityController {
    @Autowired
    lateinit var cityService: CityService

    @PostMapping("/city/addCountry")
    fun addCountry(@RequestBody request: CityRequest): City? =
        request.city?.let { cityService.saveCity(it) }

    @GetMapping("/city/name/{name}")
    fun getCityByName(@PathVariable name: String) = cityService.showCityName(name)

    @PutMapping("/city/update")
    fun updateCity(@RequestBody request: CityRequest) =
        request.city?.let { cityService.updateCity(it) }

    @DeleteMapping("/city/delete/{id}")
    fun deleteCity(@PathVariable id: Int) = cityService.removeById(id)
}
