package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class User {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotNull
    lateinit var username: String

    @NotNull
    lateinit var password: String

    @NotBlank
    lateinit var firstName: String

    @NotBlank
    lateinit var lastName: String

    @NotNull
    lateinit var email: String

    @ManyToOne
    lateinit var role: Role

    @ManyToMany
    lateinit var courses: List<Course>

    @ManyToMany
    lateinit var assignments: List<Course>
}
