package com.example.jwt_kotlin.repository

import com.example.jwt_kotlin.dto.CitySearchCriteria
import com.example.jwt_kotlin.entity.City
import com.example.jwt_kotlin.model.CityPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


@Repository
class CityCriteriaRepository(entityManager: EntityManager) {

    @Autowired
    lateinit var entityManager: EntityManager

    var criteriaBuilder: CriteriaBuilder = entityManager.criteriaBuilder


    fun findAllWithFilters(
        cityPage: CityPage,
        citySearchCriteria: CitySearchCriteria
    ): Page<City> {
        val criteriaQuery: CriteriaQuery<City> = criteriaBuilder.createQuery(City::class.java)
        val cityRoot: Root<City> = criteriaQuery.from(City::class.java)
        val predicate: Predicate = getPredicate(citySearchCriteria, cityRoot)
        criteriaQuery.where(predicate)
        setOrder(cityPage, criteriaQuery, cityRoot)

        val typedQuery: TypedQuery<City> = entityManager.createQuery(criteriaQuery)

        typedQuery.firstResult = cityPage.pageNumber * cityPage.pageSize
        typedQuery.maxResults = cityPage.pageSize
        
        val pageable: Pageable = getPageable(cityPage)
        
        val cityCount: Long? = getCitiesCount(predicate)

    }

    private fun getCitiesCount(predicate: Predicate): Long? {
        val countQuery: CriteriaQuery<Long> = criteriaBuilder.createQuery(Long::class.java)
        val countRoot: Root<City> = countQuery.from(City::class.java)
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate)

        return entityManager.createQuery(countQuery).singleResult

    }

    private fun getPageable(cityPage: CityPage): Pageable {
        val sort: Sort = Sort.by(cityPage.sortDirection, cityPage.sortBy)
        return PageRequest.of(cityPage.pageNumber, cityPage.pageSize, sort)

    }

    private fun getPredicate(citySearchCriteria: CitySearchCriteria,
                             cityRoot: Root<City>): Predicate {
        val predicates: MutableList<Predicate> = ArrayList()

        if (Objects.nonNull(citySearchCriteria.name)) {
            predicates.add(
                criteriaBuilder.like(cityRoot["name"], "%" + citySearchCriteria.name.toString() + "%")
            )
        }

        return criteriaBuilder.and(*predicates.toTypedArray())
    }


    private fun setOrder(cityPage: CityPage, criteriaQuery: CriteriaQuery<City>, cityRoot: Root<City>) {
        if (cityPage.sortDirection == Sort.Direction.ASC) {
            criteriaQuery.orderBy(criteriaBuilder.asc(cityRoot.get(cityPage.sortBy)))
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(cityRoot.get(cityPage.sortBy)))
        }
    }

}