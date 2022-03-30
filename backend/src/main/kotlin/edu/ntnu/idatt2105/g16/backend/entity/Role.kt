package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.*

@Entity
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Enumerated(EnumType.STRING)
    lateinit var name: RoleName
}