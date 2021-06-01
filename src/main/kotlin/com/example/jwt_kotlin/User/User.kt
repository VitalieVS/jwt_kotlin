package com.example.jwt_kotlin.User

import com.sun.istack.NotNull
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
 data class User(
    @Id
    @GeneratedValue
    var id: Int,
    @NotNull
    var username: String,
    @NotNull
    var password: String,
    @NotNull
    var email: String
)
