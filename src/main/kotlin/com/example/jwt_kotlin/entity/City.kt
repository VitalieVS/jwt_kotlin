package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
data class City(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    var name: String? = null,
    @OneToMany(targetEntity = Region::class, cascade = [CascadeType.ALL])
    @JoinColumn(name = "regions_id", referencedColumnName = "id")
    var regions: List<Region>? = null
)
