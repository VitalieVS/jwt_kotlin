package com.example.jwt_kotlin.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
class City(
    @Id
    @GeneratedValue
    private var id: Int = 0,
    var name: String? = null,
    @OneToMany(targetEntity = Region::class, cascade = [CascadeType.ALL])
    @JoinColumn(name = "countries_id", referencedColumnName = "id")
    var regions: List<Region>? = null
)
