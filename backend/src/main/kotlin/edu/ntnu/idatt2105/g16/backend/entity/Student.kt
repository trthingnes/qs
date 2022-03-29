package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.Entity
import javax.persistence.ManyToMany

@Entity
class Student: User() {
    @ManyToMany
    var teachingAssistantCourses: List<Course> = listOf()
}