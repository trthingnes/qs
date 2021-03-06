package edu.ntnu.idatt2105.g16.backend.entity

import edu.ntnu.idatt2105.g16.backend.dto.AssignmentDTO
import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.validation.constraints.NotNull

@Entity
@ApiModel(description = "An assignment given as part of a course.")
class Assignment() {
    constructor(dto: AssignmentDTO) : this() {
        update(dto)
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
    var users: MutableList<User> = mutableListOf()

    fun update(dto: AssignmentDTO) {
        dto.course?.let { this.course = it }
        dto.ordinal?.let { this.ordinal = it }
    }
}
