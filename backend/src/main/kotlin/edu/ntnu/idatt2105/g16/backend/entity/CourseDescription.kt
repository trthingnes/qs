package edu.ntnu.idatt2105.g16.backend.entity

import edu.ntnu.idatt2105.g16.backend.dto.CourseDescriptionDTO
import org.hibernate.validator.constraints.URL
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.NotBlank

@Entity
class CourseDescription() {
    constructor(dto: CourseDescriptionDTO): this() {
        this.code = dto.code
        this.name = dto.name
        this.url = dto.url
        this.description = dto.description
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

    @OneToMany
    var courses: List<Course> = listOf()
}
