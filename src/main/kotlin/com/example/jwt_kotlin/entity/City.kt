package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "city")
data class City(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Int = 0
)
