package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permissions")
data class Permission(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    var name: String? = null

)
