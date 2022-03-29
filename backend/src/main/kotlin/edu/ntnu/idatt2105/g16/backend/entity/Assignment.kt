package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.validation.constraints.NotNull

@Entity
class Assignment {
    @Id
    @GeneratedValue
    var id: Long = 0
    
    @ManyToOne
    var course: Course = Course()

    @ManyToMany
    var users: List<Student> = listOf()

    @NotNull
    var approved: Boolean = false
}