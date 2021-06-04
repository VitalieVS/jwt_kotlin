package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Int = 0,
    var username: String? = null,
    var password: String? = null,
    var email: String? = null,
    var roles: String? = null,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "users_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var role: Role? = null
)
