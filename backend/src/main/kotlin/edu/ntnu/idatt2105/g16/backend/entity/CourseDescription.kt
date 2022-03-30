package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.NotBlank

@Entity
class CourseDescription {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotBlank
    lateinit var code: String

    @NotBlank
    lateinit var name: String

    @NotBlank
    lateinit var url: String

    lateinit var description: String

    @OneToMany
    lateinit var courses: List<Course>
}
