package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
open class User {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotBlank
    @Size(min = 1, max = 50)
    var firstName: String = ""

    @NotBlank
    @Size(min = 1, max = 50)
    var lastName: String = ""

    @Email
    var email: String = ""

    @NotNull
    var admin: Boolean = false
}