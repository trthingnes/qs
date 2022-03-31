package edu.ntnu.idatt2105.g16.backend.dto

import edu.ntnu.idatt2105.g16.backend.entity.Course

data class AssignmentDTO(
    val ordinal: Int,
    val course: Course
)