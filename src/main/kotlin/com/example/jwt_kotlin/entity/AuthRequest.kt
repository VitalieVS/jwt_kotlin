package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
data class AuthRequest(
    val username: String,
    val password: String
)
