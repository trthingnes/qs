package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.CourseDTO
import edu.ntnu.idatt2105.g16.backend.entity.Course
import edu.ntnu.idatt2105.g16.backend.entity.Queue
import edu.ntnu.idatt2105.g16.backend.repository.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
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

    @PostMapping("/add")
    fun addCourse(@RequestBody data: CourseDTO): ResponseEntity<Any> {
        val course = Course(data)
        return ResponseEntity.ok(courseRepository.save(course))
    }
}
