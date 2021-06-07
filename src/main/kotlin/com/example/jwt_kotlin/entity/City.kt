package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import javax.persistence.*

@Entity
@AllArgsConstructor
@Table(name = "cities")
data class City(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    var name: String? = null
)
