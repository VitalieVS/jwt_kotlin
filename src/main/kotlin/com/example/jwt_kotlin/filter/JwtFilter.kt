package com.example.jwt_kotlin.filter

import com.sun.istack.NotNull
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        @NotNull response: HttpServletResponse,
        @NotNull filterChain: FilterChain
    ) {
        var authorizationHeader: String = request.getHeader("Authorization")
        var token: String? = null
        var username: String? = null

        if (authorizationHeader.startsWith("Bearer")) {
            token = authorizationHeader.substring(7)
            // implement jwutil here
        }
    }

}