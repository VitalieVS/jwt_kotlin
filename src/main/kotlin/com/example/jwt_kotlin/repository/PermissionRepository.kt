package com.example.jwt_kotlin.repository

import com.example.jwt_kotlin.entity.Permission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PermissionRepository : JpaRepository<Permission, Int>{

}