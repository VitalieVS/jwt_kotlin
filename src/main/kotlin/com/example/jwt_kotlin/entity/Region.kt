package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.Data
import javax.persistence.*

@Data
@AllArgsConstructor
@Entity
@Table(name = "regions")
data class Region (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    var name: String? = null,
    @OneToMany(targetEntity = City::class, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    var cities: List<City>? = null
)
