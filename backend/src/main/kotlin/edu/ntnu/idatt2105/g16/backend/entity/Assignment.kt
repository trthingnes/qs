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
    lateinit var course: Course

    @ManyToMany
    lateinit var users: List<User>

    @NotNull
    var approved: Boolean = false
}
