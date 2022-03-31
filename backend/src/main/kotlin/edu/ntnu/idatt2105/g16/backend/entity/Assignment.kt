package edu.ntnu.idatt2105.g16.backend.entity

import edu.ntnu.idatt2105.g16.backend.dto.AssignmentDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.validation.constraints.NotNull

@Entity
class Assignment() {
    constructor(dto: AssignmentDTO) : this() {
        ordinal = dto.ordinal
        course = dto.course
    }

    @Id
    @GeneratedValue
    var id: Long = 0

    @NotNull(message = "Ordinal cannot be null")
    var ordinal: Int = 0

    @ManyToOne
    @NotNull(message = "Course cannot be null")
    lateinit var course: Course

    @ManyToMany
    var users: List<User> = listOf()
}
