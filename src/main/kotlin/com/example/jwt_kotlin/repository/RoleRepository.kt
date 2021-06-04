package com.example.jwt_kotlin.repository

import com.example.jwt_kotlin.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Int> {
    fun findRoleByRoleName(roleName: String?): Role?
}