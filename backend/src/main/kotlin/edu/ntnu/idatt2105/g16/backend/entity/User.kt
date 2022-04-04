package edu.ntnu.idatt2105.g16.backend.entity

import edu.ntnu.idatt2105.g16.backend.dto.UserDTO
import io.swagger.annotations.ApiModel
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@ApiModel(description = "A user that can attend courses and enter queues.")
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
    var role: Role = Role.STUDENT

    @ManyToMany
    var studentCourses: MutableList<Course> = mutableListOf()

    @ManyToMany
    var assistantCourses: MutableList<Course> = mutableListOf()

    @ManyToMany
    var teacherCourses: MutableList<Course> = mutableListOf()

    fun update(dto: UserDTO) {
        dto.username?.let { this.username = it }
        dto.password?.let { this.password = it }
        dto.firstName?.let { this.firstName = it }
        dto.lastName?.let { this.lastName = it }
        dto.email?.let { this.email = it }
        dto.role?.let { this.role = it }
    }
}
