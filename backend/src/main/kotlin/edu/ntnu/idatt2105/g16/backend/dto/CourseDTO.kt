package edu.ntnu.idatt2105.g16.backend.dto

import edu.ntnu.idatt2105.g16.backend.entity.*

data class CourseDTO(
    val id: Long?,
    val code: String?,
    val name: String?,
    val url: String?,
    val description: String?,
    val semester: Semester?,
    val year: Int?,
    val numAssignments: Int?
) {
    constructor(c: Course) : this(c.id, c.code, c.name, c.url, c.description, c.semester, c.year, null)
}
