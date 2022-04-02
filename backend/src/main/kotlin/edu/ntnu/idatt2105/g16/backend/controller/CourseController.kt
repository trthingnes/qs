package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.CourseDTO
import edu.ntnu.idatt2105.g16.backend.repository.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/courses")
class CourseController {
    @Autowired
    private lateinit var courseRepository: CourseRepository

    @GetMapping("/{id}")
    fun getCourseById(@PathVariable id: Long): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findCourseById(id)

        return if(optionalCourse.isPresent) {
            ResponseEntity.ok(CourseDTO(optionalCourse.get()))
        } else {
            ResponseEntity.badRequest().body("Course not found.")
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER')")
    fun updateCourseById(@PathVariable id: Long, @RequestBody dto: CourseDTO): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findCourseById(id)
        if (!optionalCourse.isPresent) {
            return ResponseEntity.badRequest().body("Course not found.")
        }

        val course = optionalCourse.get()
        course.update(dto)

        return ResponseEntity.ok(CourseDTO(courseRepository.save(course)))
    }
}
