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

    @Autowired
    lateinit var userRepository: UserRepository

    protected val ADMIN_ACCESS = arrayOf("ROLE_ADMIN")
    protected val USER_ACCESS = arrayOf("ROLE_USER")

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

    @GetMapping("/access/{userId}/{userRole}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Secured("ROLE_ADMIN")
    fun giveAccess(
        @PathVariable userId: Int,
        @PathVariable userRole: String,
        principal: Principal
    ): String? {
        val user: User = userRepository.findById(userId).get()
        val currRoles = getRolesOfLoggedUser(principal)
        var newRole = ""
        if (currRoles.contains(userRole)) {
            newRole = user.roles.toString() + "," + userRole
            user.roles = newRole
            userRepository.save(user)
            return "Granted Access for:" + user.username
        }

        return "Not sufficient rights to perform this operation!"
    }

    private fun getRolesOfLoggedUser(principal: Principal): List<String> {
        val roles: String? = getLoggedUser(principal).roles
        val assignRoles = Arrays.stream(
            roles?.split(",")?.toTypedArray()
        ).collect(Collectors.toList())

        if (assignRoles.contains("ROLE_ADMIN"))
            return Arrays.stream(ADMIN_ACCESS)
                .collect(Collectors.toList())

        return if (assignRoles.contains("ROLE_USER")) Arrays.stream(USER_ACCESS).collect(
            Collectors.toList()
        ) else emptyList()
    }

    private fun getLoggedUser(principal: Principal): User {
        return userRepository.findUserByUsername(principal.name)!!.get()
    }
}