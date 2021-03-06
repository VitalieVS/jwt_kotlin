package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import javax.persistence.*

@Entity
@AllArgsConstructor
@Table(name = "roles")
data class Role(
    @Id
    @GeneratedValue
    var id: Int? = null,
    @Column(name = "role_name")
    var roleName: String? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "roles_permissions",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id", referencedColumnName = "id")]
    )
    var permissions: Collection<Permission>? = null
)
