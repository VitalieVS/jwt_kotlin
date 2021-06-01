package com.example.jwt_kotlin.service.country_service

import com.example.jwt_kotlin.entity.Country
import com.example.jwt_kotlin.repository.CountryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CountryService {

    @Autowired
    lateinit var countryRepository: CountryRepository

    fun saveCountry(country: Country): Country = countryRepository.save(country)

    fun showCountryName(name: String) = countryRepository.findByName(name)
    
    fun getCountries(): MutableList<Country> = countryRepository.findAll()

    fun updateCountry(country: Country) : Country {
        val existingCountry = country.id?.let { countryRepository.findById(it).orElse(null) }!!
        existingCountry.name = country.name
        existingCountry.peopleCount = country.peopleCount
        existingCountry.cities = country.cities

        return countryRepository.save(existingCountry)
    }
    
}