package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.validation.constraints.NotNull

@Entity
class Course {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotNull
    var semester: Semester = Semester.OTHER

    @NotNull
    var year: Int = 0

    @ManyToOne
    @NotNull
    var info: CourseDescription = CourseDescription()

    @OneToOne
    @NotNull
    var queue: Queue = Queue()

    @ManyToMany
    @NotNull
    var students: List<Student> = listOf()
}