package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "countries")
data class Country (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    var name: String? = null,
    @Column(name = "people_count")
    var peopleCount: Long = 0,
    @OneToMany(targetEntity = Region::class, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    var regions: List<Region>? = null
)