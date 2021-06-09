package com.example.jwt_kotlin.repository

import com.example.jwt_kotlin.dto.CitySearchCriteria
import com.example.jwt_kotlin.entity.City
import com.example.jwt_kotlin.model.CityPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import kotlin.collections.ArrayList


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
    }

    private fun getPredicate(citySearchCriteria: CitySearchCriteria,
                             cityRoot: Root<City>): Predicate {
        val predicateList: MutableList<Predicate>? = null

        if (Objects.nonNull(citySearchCriteria.name)) {
            predicateList!!.add(
                criteriaBuilder.like(cityRoot.get("name"),
                    "%" + citySearchCriteria.name )

            )
        }
    }

}