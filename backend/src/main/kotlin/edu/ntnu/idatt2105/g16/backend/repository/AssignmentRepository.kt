package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.Assignment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AssignmentRepository : CrudRepository<Assignment, Long> {
    fun findAllByCourseId(courseId: Long): Optional<List<Assignment>>
    fun findByUsers_UsernameAndCourseId(username: String, courseId: Long): Optional<List<Assignment>>
    fun findByCourseIdAndOrdinal(courseId: Long, ordinal: Int): Optional<Assignment>
}
