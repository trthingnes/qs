package edu.ntnu.idatt2105.g16.backend.entity

import edu.ntnu.idatt2105.g16.backend.dto.CourseDTO
import org.hibernate.validator.constraints.URL
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Course() {
    constructor(dto: CourseDTO) : this() {
        this.code = dto.code
        this.name = dto.name
        this.url = dto.url
        this.description = dto.description
        this.semester = dto.semester
        this.year = dto.year
        this.queue = dto.queue
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

    @OneToOne
    @NotNull(message = "Queue cannot be null")
    lateinit var queue: Queue

    @ManyToMany
    var users: List<User> = listOf()

    @OneToMany
    var assignments: List<Assignment> = listOf()
}
