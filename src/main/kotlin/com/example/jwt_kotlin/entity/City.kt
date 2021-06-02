package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city")
data class City(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    var name: String? = null,
    @OneToMany(targetEntity = Region::class, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "city_fk", referencedColumnName = "id")
    var regions: List<Region>? = null
)
