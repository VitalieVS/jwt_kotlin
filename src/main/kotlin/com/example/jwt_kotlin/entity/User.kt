package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "user")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Int = 0,
    var username: String? = null,
    var password: String? = null,
    var email: String? = null
)
