package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Teacher: User() {
    @OneToMany
    var teachingCourses: List<Course> = listOf()
}