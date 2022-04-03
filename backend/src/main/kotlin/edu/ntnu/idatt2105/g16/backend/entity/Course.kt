package edu.ntnu.idatt2105.g16.backend.entity

import edu.ntnu.idatt2105.g16.backend.dto.CourseDTO
import org.hibernate.validator.constraints.URL
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Course() {
    constructor(dto: CourseDTO) : this() {
        update(dto)
    }

    @Id
    @GeneratedValue
    var id: Long = 0

    @NotBlank(message = "Code cannot be blank")
    lateinit var code: String

    @NotBlank(message = "Name cannot be blank")
    lateinit var name: String

    @URL
    @NotBlank(message = "URL cannot be blank")
    lateinit var url: String

    lateinit var description: String

    @NotNull(message = "Semester cannot be null")
    lateinit var semester: Semester

    @NotNull(message = "Year cannot be null")
    var year: Int = 0

    @OneToMany
    var queue: MutableList<QueueEntry> = mutableListOf()

    @OneToMany
    var assignments: MutableList<Assignment> = mutableListOf()

    @ManyToMany
    var students: MutableList<User> = mutableListOf()

    @ManyToMany
    var assistants: MutableList<User> = mutableListOf()

    fun update(dto: CourseDTO) {
        dto.code?.let { this.code = it }
        dto.name?.let { this.name = it }
        dto.url?.let { this.url = it }
        dto.description?.let { this.description = it }
        dto.semester?.let { this.semester = it }
        dto.year?.let { this.year = it }
    }
}
