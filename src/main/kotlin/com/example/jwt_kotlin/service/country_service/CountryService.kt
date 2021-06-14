package com.example.jwt_kotlin.service.country_service

import com.example.jwt_kotlin.dto.CitySearchCriteria
import com.example.jwt_kotlin.dto.CountrySearchCriteria
import com.example.jwt_kotlin.entity.City
import com.example.jwt_kotlin.entity.Country
import com.example.jwt_kotlin.model.CityPage
import com.example.jwt_kotlin.model.CountryPage
import com.example.jwt_kotlin.repository.CityRepository
import com.example.jwt_kotlin.repository.CountryCriteriaRepository
import com.example.jwt_kotlin.repository.CountryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.*
import org.springframework.stereotype.Service

@Service
class CountryService {

    @Autowired
    lateinit var countryRepository: CountryRepository

    @Autowired
    lateinit var countryCriteriaRepository: CountryCriteriaRepository

    fun saveCountry(country: Country): Country = countryRepository.save(country)

    fun showCountryName(name: String) = countryRepository.findByName(name)
    
    fun getCountries(): MutableList<Country> = countryRepository.findAll()

    fun updateCountry(country: Country) : Country {
        val existingCountry = country.id?.let {
            countryRepository.findById(it).orElse(null)
        }!!
        existingCountry.name = country.name
        existingCountry.peopleCount = country.peopleCount
        existingCountry.regions = country.regions

        return countryRepository.save(existingCountry)
    }

    fun removeById(id: Int) : String {
        countryRepository.deleteById(id)
        return "Deleted id:$id"
    }

    fun getPagedCountries(countryPage: CountryPage): Page<Country?>? {
        val pageable: Pageable = PageRequest.of(
            countryPage.pageNumber,
            countryPage.pageSize
        )
        return countryRepository.findAll(pageable)
    }

    fun getCitiesId(countryPage: CountryPage, ids: List<Int>): Page<Country> {
        val pageable: Pageable = PageRequest.of(
            countryPage.pageNumber,
            1
        )
        return countryRepository.fetchCities(pageable, ids)
    }

    fun getFilteredCountries(countryPage: CountryPage,
                     countrySearchCriteria: CountrySearchCriteria
    ): PageImpl<Country>? {
        return countryCriteriaRepository.findAllWithFilters(countryPage, countrySearchCriteria)
    }
    
}