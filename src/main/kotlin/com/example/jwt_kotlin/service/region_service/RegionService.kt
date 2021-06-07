package com.example.jwt_kotlin.service.region_service

import com.example.jwt_kotlin.entity.Region
import com.example.jwt_kotlin.model.CountryPage
import com.example.jwt_kotlin.repository.RegionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class RegionService {

    @Autowired
    lateinit var regionRepository: RegionRepository

    fun getPagedFiltered(countryPage: CountryPage): List<Region> {
       // val sort: Sort = Sort.by(countryPage.sortDirection, countryPage.sortBy)
        val pageable: Pageable = PageRequest.of(
            countryPage.pageNumber,
            countryPage.pageSize
        )
        return regionRepository.findDistinctByCitiesNotNull(pageable)
    }

    fun getRegionsByName(id: Int) = regionRepository.findByIdCities(id)


}