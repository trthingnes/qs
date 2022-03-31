package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.CourseDescription
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CourseDescriptionRepository : CrudRepository<CourseDescription, Long> {
    override fun findById(id: Long): Optional<CourseDescription>
}
