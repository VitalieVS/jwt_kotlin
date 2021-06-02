package com.example.jwt_kotlin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import java.util.logging.ConsoleHandler
import java.util.logging.Handler
import java.util.logging.Level

@SpringBootApplication
class JwtKotlinApplication : CommandLineRunner {
    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    override fun run(vararg args: String?) {
        val sql = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)"
        val result = jdbcTemplate.update(sql, "admin2", "password2", "adminul2@gmail.com")
        if (result > 0) println("yes")
    }
}

fun main(args: Array<String>) {
    runApplication<JwtKotlinApplication>(*args)
}
