package com.example.jwt_kotlin.service.region_service

import com.example.jwt_kotlin.entity.City
import com.example.jwt_kotlin.repository.CityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RegionService {
    @Autowired
    lateinit var regionRepository: CityRepository

    fun getFilteredByName(id: Int): List<City> = regionRepository.findAllByName(id)

}