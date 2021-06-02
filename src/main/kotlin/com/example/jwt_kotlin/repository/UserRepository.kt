package com.example.jwt_kotlin.repository

import com.example.jwt_kotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Int> {
    fun findUserByUsername(username: String?): Optional<User?>?
}