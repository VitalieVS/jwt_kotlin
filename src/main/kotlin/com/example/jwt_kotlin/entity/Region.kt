package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "regions")
data class Region (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    var name: String? = null
)
