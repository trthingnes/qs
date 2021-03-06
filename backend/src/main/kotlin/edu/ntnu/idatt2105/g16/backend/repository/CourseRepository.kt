package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.Course
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CourseRepository : CrudRepository<Course, Long> {
    fun findCourseById(id: Long): Optional<Course>
}
