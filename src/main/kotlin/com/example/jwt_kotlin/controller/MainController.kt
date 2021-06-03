package com.example.jwt_kotlin.controller

import com.example.jwt_kotlin.dto.AuthRequest
import com.example.jwt_kotlin.entity.User
import com.example.jwt_kotlin.repository.UserRepository
import com.example.jwt_kotlin.util.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*
import java.util.stream.Collectors

@RestController
class MainController {
    @Autowired
    lateinit var jwtUtil: JwtUtil

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @GetMapping("/")
    fun welcome(): String = "Main Page"

    @PostMapping("/auth")
    fun generateToken(@RequestBody authRequest: AuthRequest): String? {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
            )

        } catch (e: Exception) {
            throw java.lang.Exception("Invalid username or password")
        }
        return jwtUtil.generateToken(authRequest.username)
    }

}