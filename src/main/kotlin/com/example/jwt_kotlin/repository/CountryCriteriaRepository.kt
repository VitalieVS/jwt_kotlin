package com.example.jwt_kotlin.repository

import com.example.jwt_kotlin.dto.CountrySearchCriteria
import com.example.jwt_kotlin.entity.City
import com.example.jwt_kotlin.entity.Country
import com.example.jwt_kotlin.entity.Region
import com.example.jwt_kotlin.model.CountryPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.*
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import javax.persistence.criteria.*


@Repository
class CountryCriteriaRepository(entityManager: EntityManager) {

    @Autowired
    lateinit var entityManager: EntityManager

    var criteriaBuilder: CriteriaBuilder = entityManager.criteriaBuilder

    fun findAllWithFilters(
        countryPage: CountryPage,
        countrySearchCriteria: CountrySearchCriteria,
    ): PageImpl<Country>? {
        val criteriaQuery: CriteriaQuery<Country> = criteriaBuilder.createQuery(Country::class.java)
        val countryRoot: Root<Country> = criteriaQuery.from(Country::class.java)

        val predicate: Predicate = getPredicate(countrySearchCriteria, countryRoot)

//         criteriaQuery.select(countryRoot).where(countryRoot.`in`(countrySearchCriteria.ids))
//             .where(predicate)
        criteriaQuery.select(countryRoot).where(predicate)

        val typedQuery: TypedQuery<Country> = entityManager.createQuery(criteriaQuery)

        typedQuery.firstResult = countryPage.pageNumber * countryPage.pageSize
        typedQuery.maxResults = countryPage.pageSize
        val countryCount: Long? = getCountryCount(predicate)
        val pageable: Pageable = getPageable(countryPage)

        return countryCount?.let { PageImpl(typedQuery.resultList, pageable, it) }
    }

    private fun getCountryCount(predicate: Predicate): Long? {
        val countQuery: CriteriaQuery<Long> = criteriaBuilder.createQuery(Long::class.java)
        val countRoot: Root<Country> = countQuery.from(Country::class.java)
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate)

        return entityManager.createQuery(countQuery).singleResult
    }

    private fun getPageable(countryPage: CountryPage): Pageable {
        val sort: Sort = Sort.by(countryPage.sortDirection, countryPage.sortBy)
        return PageRequest.of(countryPage.pageNumber, countryPage.pageSize, sort)
    }

    private fun getPredicate(
        countrySearchCriteria: CountrySearchCriteria,
        countryRoot: Root<Country>
    ): Predicate {
        val predicates: MutableList<Predicate> = ArrayList()

        lateinit var joinRegions: Join<Country, Region>
       // lateinit var joinCities: Join<Region, City>

        if (Objects.nonNull(countrySearchCriteria.name)) {
            predicates.add(
                criteriaBuilder.like(countryRoot["name"], "%" + countrySearchCriteria.name.toString() + "%")
            )
        }

        if (countrySearchCriteria.peopleCount > 0) {
            predicates.add(
                criteriaBuilder.equal(countryRoot.get<Long>("peopleCount"), countrySearchCriteria.peopleCount)
            )
        }

        if (Objects.nonNull(countrySearchCriteria.regionName)) {
            joinRegions = countryRoot.join("regions", JoinType.INNER)
            joinRegions.alias("name")
            predicates.add(
                criteriaBuilder.like(
                    joinRegions.get("name"), "%" + countrySearchCriteria.regionName.toString() + "%"
                )
            )
        }

        return criteriaBuilder.and(*predicates.toTypedArray())
    }

}