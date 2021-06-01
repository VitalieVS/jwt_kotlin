package com.example.jwt_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JwtKotlinApplication

fun main(args: Array<String>) {
	runApplication<JwtKotlinApplication>(*args) 
}
