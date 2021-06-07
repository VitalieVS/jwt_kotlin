package com.example.jwt_kotlin.service.region_service

import com.example.jwt_kotlin.entity.Region
import com.example.jwt_kotlin.repository.RegionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RegionService {

    @Autowired
    lateinit var regionRepository: RegionRepository

    fun saveRegion(region: Region) = regionRepository.save(region)

    fun showRegionName(name: String) = regionRepository.findByName(name)

    fun getRegions(): MutableList<Region> = regionRepository.findAll()

    fun updateRegion(region: Region): Region {
        val existingRegion = region.id?.let {
            regionRepository.findById(it).orElse(null)
        }!!
        existingRegion.name = region.name
        existingRegion.cities = region.cities

        return regionRepository.save(existingRegion)
    }

    fun removeRegionById(id: Int): String {
        regionRepository.deleteById(id)
        return "Deleted id:$id"
    }

}