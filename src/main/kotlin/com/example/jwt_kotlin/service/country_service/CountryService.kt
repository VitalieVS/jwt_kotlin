package com.example.jwt_kotlin.service.country_service

import com.example.jwt_kotlin.entity.Country
import com.example.jwt_kotlin.model.CountryPage
import com.example.jwt_kotlin.repository.CountryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
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
        existingCountry.regions = country.regions

        return countryRepository.save(existingCountry)
    }

    fun removeById(id: Int) : String {
        countryRepository.deleteById(id)
        return "Deleted id:$id"
    }

    fun getPagedCountries(countryPage: CountryPage): Page<Country?>? {
        val sort: Sort = Sort.by(countryPage.sortDirection, countryPage.sortBy)
        val pageable: Pageable = PageRequest.of(
            countryPage.pageNumber,
            countryPage.pageSize, sort
        )
        return countryRepository.findAll(pageable)
    }
    
}