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
        update(dto)
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
    var studentCourses: List<Course> = listOf()

    @ManyToMany
    var assistantCourses: List<Course> = listOf()

    @ManyToMany
    var teacherCourses: List<Course> = listOf()

    fun update(dto: UserDTO) {
        dto.username?.let { this.username = it }
        dto.password?.let { this.password = it }
        dto.firstName?.let { this.firstName = it }
        dto.lastName?.let { this.lastName = it }
        dto.email?.let { this.email = it }
        dto.role?.let { this.role = it }
    }
}
