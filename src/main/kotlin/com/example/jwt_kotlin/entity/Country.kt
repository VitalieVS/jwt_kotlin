package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
data class Country (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    var name: String? = null,
    var peopleCount: Long = 0,
    @OneToMany(targetEntity = City::class, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "country_fk", referencedColumnName = "id")
    var cities: List<City>? = null
)