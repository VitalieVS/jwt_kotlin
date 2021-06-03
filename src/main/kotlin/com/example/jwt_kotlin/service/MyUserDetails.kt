package com.example.jwt_kotlin.service

import com.example.jwt_kotlin.entity.Role
import com.example.jwt_kotlin.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class MyUserDetails(
    user: Optional<User?>?
) : UserDetails {

    private var user: Optional<User?>? = user


    override fun getAuthorities(): List<GrantedAuthority> {
        val role: Role? = user?.get()?.role

        val authorities: MutableList<SimpleGrantedAuthority> = ArrayList()

        authorities.add(SimpleGrantedAuthority(role?.roleName))

        return authorities
    }

    override fun getPassword(): String? = user?.get()?.password

    override fun getUsername(): String? = user?.get()?.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}