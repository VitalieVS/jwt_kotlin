package com.example.jwt_kotlin.repository.security

import com.example.jwt_kotlin.User.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findUserByUsername(username: String): User
}