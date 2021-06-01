package com.example.jwt_kotlin.service

import com.example.jwt_kotlin.User.User
import com.example.jwt_kotlin.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService : UserDetailsService {
    @Autowired
    private lateinit var repository: UserRepository
    override fun loadUserByUsername(username: String?): UserDetails {
        val user: User? = username?.let { repository.findUserByUsername(it) }
            return org.springframework.security.core.userdetails.User(
                user?.username, user?.password, ArrayList()
            )

    }


}