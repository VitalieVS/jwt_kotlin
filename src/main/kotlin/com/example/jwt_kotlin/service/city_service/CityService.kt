package com.example.jwt_kotlin.service.city_service

import com.example.jwt_kotlin.entity.City
import com.example.jwt_kotlin.entity.Region
import com.example.jwt_kotlin.model.CityPage
import com.example.jwt_kotlin.repository.CityRepository
import com.example.jwt_kotlin.repository.RegionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CityService {

    @Autowired
    lateinit var regionRepository: RegionRepository

    @Autowired
    lateinit var cityRepository: CityRepository


    fun getPagedFiltered(cityPage: CityPage): Page<Region>? {
        val pageable: Pageable = PageRequest.of(
            cityPage.pageNumber,
            cityPage.pageSize
        )
        return regionRepository.findDistinctByCitiesNotNull(pageable)
    }

    fun getCitiesByRegionId(cityPage: CityPage, ids: List<Int>): Page<City>? {
        val pageable: Pageable = PageRequest.of(
            cityPage.pageNumber,
            cityPage.pageSize
        )


        return cityRepository.findByIdCities(pageable, ids)
    }

    fun saveCity(city: City): City = cityRepository.save(city)

    fun showCityName(name: String) = cityRepository.findByName(name)

    fun getCities(): MutableList<City> = cityRepository.findAll()

    fun updateCity(city: City): City {
        val existingCity = city.id?.let {
            cityRepository.findById(it).orElse(null)
        }!!
        existingCity.name = city.name

        return cityRepository.save(existingCity)
    }

    fun removeById(id: Int): String {
        cityRepository.deleteById(id)
        return "Deleted id:$id"
    }
}