package com.example.jwt_kotlin.security

import com.example.jwt_kotlin.service.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var myUserDetailsService: MyUserDetailsService

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()?.authorizeRequests()?.antMatchers("/auth")?.permitAll()?.anyRequest()?.authenticated()
            ?.and()?.exceptionHandling()?.and()?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //to implement filter
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(myUserDetailsService)
    }

    @Bean(name = [BeanIds.AUTHENTICATION_MANAGER])
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder = NoOpPasswordEncoder.getInstance()
}