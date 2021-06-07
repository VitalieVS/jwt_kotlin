package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import javax.persistence.*

@Entity
@AllArgsConstructor
@Table(name = "permissions")
data class Permission(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    var name: String? = null

)
