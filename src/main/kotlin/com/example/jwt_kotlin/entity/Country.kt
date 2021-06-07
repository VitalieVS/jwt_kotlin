package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import javax.persistence.*

@AllArgsConstructor
@Entity
@Table(name = "countries")
data class Country(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    var name: String? = null,
    @Column(name = "people_count")
    var peopleCount: Long = 0,
    @OneToMany(targetEntity = Region::class, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    var regions: List<Region>? = null
)