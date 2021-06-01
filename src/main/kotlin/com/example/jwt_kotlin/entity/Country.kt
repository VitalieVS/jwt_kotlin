package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "countries")
data class Country (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    var name: String? = null,
    var peopleCount: Long = 0,
    @OneToMany(targetEntity = City::class, cascade = [CascadeType.ALL])
    @JoinColumn(name = "countries_id", referencedColumnName = "id")
    var cities: List<City>? = null
)