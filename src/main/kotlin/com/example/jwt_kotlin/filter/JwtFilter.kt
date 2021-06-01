package com.example.jwt_kotlin.filter

import com.example.jwt_kotlin.service.MyUserDetailsService
import com.example.jwt_kotlin.util.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter : OncePerRequestFilter() {
    @Autowired
    private val jwtUtil: JwtUtil? = null

    @Autowired
    private val service: MyUserDetailsService? = null

    override fun doFilterInternal(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = httpServletRequest.getHeader("Authorization")
        var token: String? = null
        var username: String? = null
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            token = authorizationHeader.substring(7)
            username = jwtUtil!!.extractUsername(token)
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = service!!.loadUserByUsername(username)
            if (jwtUtil!!.validateToken(token, userDetails)!!) {
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                usernamePasswordAuthenticationToken.details =
                    WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse)
    }


}