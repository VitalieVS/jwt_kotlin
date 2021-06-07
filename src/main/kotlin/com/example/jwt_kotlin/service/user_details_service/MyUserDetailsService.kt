package com.example.jwt_kotlin.service.user_details_service

import com.example.jwt_kotlin.entity.Permission
import com.example.jwt_kotlin.repository.RoleRepository
import com.example.jwt_kotlin.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class MyUserDetailsService : UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var roleRepository: RoleRepository

    override fun loadUserByUsername(username: String?): UserDetails? {
        val user: Optional<com.example.jwt_kotlin.entity.User?>? = userRepository.findUserByUsername(username)

        if (!user!!.isPresent()) throw UsernameNotFoundException("Does not exist")

        return User(
            user.get().username, user.get().password, true,
            true, true, true,
            getAuthorities(
                listOf(
                    roleRepository.findRoleByRoleName(user.get().role?.roleName)
                )
            )
        )
    }

    private fun getAuthorities(
        roles: List<com.example.jwt_kotlin.entity.Role?>
    ): Collection<GrantedAuthority?> {
        return getGrantedAuthorities(getPermissions(roles))
    }

    private fun getGrantedAuthorities(permissions: List<String>): List<GrantedAuthority?> {
        val authorities: MutableList<GrantedAuthority?> = ArrayList()
        for (permission in permissions) {
            authorities.add(SimpleGrantedAuthority(permission))
        }
        return authorities
    }

    private fun getPermissions(roles: List<com.example.jwt_kotlin.entity.Role?>): List<String> {
        val permissions: MutableList<String> = ArrayList()
        val collection: MutableList<Permission> = ArrayList()
        for (role in roles) {
            role?.permissions?.let { collection.addAll(it) }
        }
        for (item in collection) {
            item.name?.let { permissions.add(it) }
        }
        return permissions
    }
}