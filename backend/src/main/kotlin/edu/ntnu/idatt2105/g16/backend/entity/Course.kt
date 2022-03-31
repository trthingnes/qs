package edu.ntnu.idatt2105.g16.backend.entity

import edu.ntnu.idatt2105.g16.backend.dto.CourseDTO
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Course() {
    constructor(dto: CourseDTO) : this() {
        this.info = dto.info
        this.semester = dto.semester
        this.year = dto.year
        this.queue = dto.queue
    }

    @Id
    @GeneratedValue
    var id: Long = 0

    @ManyToOne
    lateinit var info: CourseDescription

    @NotNull(message = "Semester cannot be null")
    lateinit var semester: Semester

    @NotNull(message = "Year cannot be null")
    var year: Int = 0

    @OneToOne
    @NotNull(message = "Queue cannot be null")
    lateinit var queue: Queue

    @ManyToMany
    var users: List<User> = listOf()

    @OneToMany
    var assignments: List<Assignment> = listOf()
}
