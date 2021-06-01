package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "region")
data class Region (
    @Id
    @GeneratedValue
    private val id: Int = 0,
    private val name: String? = null,
)
