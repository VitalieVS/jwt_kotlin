package com.example.jwt_kotlin.service

import com.example.jwt_kotlin.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.function.Supplier

@Service
class MyUserDetailsService : UserDetailsService {
    @Autowired
    private lateinit var repository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails? {
        val user = repository.findUserByUsername(username)

        return user?.map<UserDetails> {it-> MyUserDetails(it) }?.orElseThrow(Supplier {
            UsernameNotFoundException(
                "Does not exist in system"
            )
        })
    }
}