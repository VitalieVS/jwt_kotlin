package com.example.jwt_kotlin.User

import com.sun.istack.NotNull
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
 data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int,
    @NotNull
    var username: String,
    @NotNull
    var password: String,
    @NotNull
    var email: String
)
