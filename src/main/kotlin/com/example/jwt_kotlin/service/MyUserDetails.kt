package com.example.jwt_kotlin.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MyUserDetails(user: com.example.jwt_kotlin.entity.User?
): UserDetails {
    private var username: String? = null
    private var password: String? = null
    private var email: String? = null
    private var grantedAuthorityList: List<GrantedAuthority>? = null

    init {
        username = user?.username
        password = user?.password
        email = user?.email
        grantedAuthorityList = user?.roles?.split(",")?.map { role: String? -> SimpleGrantedAuthority(role) }
    }


    override fun getAuthorities(): List<GrantedAuthority>? = grantedAuthorityList

    override fun getPassword(): String? = password

    override fun getUsername(): String? = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}