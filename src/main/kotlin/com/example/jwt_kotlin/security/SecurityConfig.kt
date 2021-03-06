package com.example.jwt_kotlin.security

import com.example.jwt_kotlin.filter.JwtFilter
import com.example.jwt_kotlin.repository.PermissionRepository
import com.example.jwt_kotlin.repository.RoleRepository
import com.example.jwt_kotlin.service.user_details_service.MyUserDetailsService
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
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var jwtFilter: JwtFilter

    @Autowired
    lateinit var myUserDetailsService: MyUserDetailsService

    @Autowired
    lateinit var permissionRepository: PermissionRepository

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService?>(myUserDetailsService)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? = NoOpPasswordEncoder.getInstance()

    @Bean(name = [BeanIds.AUTHENTICATION_MANAGER])
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager? = super.authenticationManagerBean()


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        permissionRepository.findAll().forEach {
            http.authorizeRequests()
                .antMatchers(it.name).hasAuthority(it.name)
        }

        http.authorizeRequests()
            .antMatchers("/auth").permitAll().anyRequest()
            .authenticated().and().exceptionHandling()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}