package com.example.jwt_kotlin.controller

import com.example.jwt_kotlin.entity.AuthRequest
import com.example.jwt_kotlin.util.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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