package edu.ntnu.idatt2105.g16.backend.dto

import edu.ntnu.idatt2105.g16.backend.entity.*

data class CourseDTO(
    val code: String,
    val name: String,
    val url: String,
    val description: String,
    val semester: Semester,
    val year: Int,
) {
    constructor(c: Course): this(c.code, c.name, c.url, c.description, c.semester, c.year)
}
