package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Course {
    @Id
    @GeneratedValue
    var id: Long = 0

    @ManyToOne
    lateinit var info: CourseDescription

    @NotNull
    lateinit var semester: Semester

    @NotNull
    var year: Int = 0

    @OneToOne
    lateinit var queue: Queue

    @ManyToMany
    lateinit var users: List<User>

    @OneToMany
    lateinit var assignments: List<Assignment>
}
