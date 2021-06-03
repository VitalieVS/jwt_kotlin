package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
data class Role(
    @Id
    @GeneratedValue
    var id: Int? = null,
    @Column(name = "role_name")
    var roleName: String? = null
)
