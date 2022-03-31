package edu.ntnu.idatt2105.g16.backend.entity

import edu.ntnu.idatt2105.g16.backend.dto.UserDTO
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class User() {
    constructor (dto: UserDTO) : this() {
        this.username = dto.username
        this.password = dto.password
        this.firstName = dto.firstName
        this.lastName = dto.lastName
        this.email = dto.email
        this.role = dto.role
    }

    @Id
    @NotNull(message = "Username cannot be null")
    lateinit var username: String

    @NotNull(message = "Password cannot be null")
    lateinit var password: String

    @NotBlank(message = "First name cannot be blank")
    lateinit var firstName: String

    @NotBlank(message = "Last name cannot be blank")
    lateinit var lastName: String

    @NotNull(message = "Email cannot be null")
    lateinit var email: String

    @NotNull(message = "Role cannot be null")
    lateinit var role: Role

    @ManyToMany
    var courses: List<Course> = listOf()
}
