package com.example.jwt_kotlin.service.city_service

import com.example.jwt_kotlin.entity.City
import com.example.jwt_kotlin.entity.Region
import com.example.jwt_kotlin.model.CountryPage
import com.example.jwt_kotlin.repository.CityRepository
import com.example.jwt_kotlin.repository.RegionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CityService {

    @Autowired
    lateinit var regionRepository: RegionRepository

    @Autowired
    lateinit var cityRepository: CityRepository

    fun getPagedFiltered(countryPage: CountryPage): List<Region> {
        val pageable: Pageable = PageRequest.of(
            countryPage.pageNumber,
            countryPage.pageSize
        )
        return regionRepository.findDistinctByCitiesNotNull(pageable)
    }

    fun getCitiesByRegionId(countryPage: CountryPage, id: Int): List<City> {
        val pageable: Pageable = PageRequest.of(
            countryPage.pageNumber,
            countryPage.pageSize
        )
        return cityRepository.findByIdCities(pageable, id)
    }


}