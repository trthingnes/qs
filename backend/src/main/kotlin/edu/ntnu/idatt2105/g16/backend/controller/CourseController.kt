package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.CourseDTO
import edu.ntnu.idatt2105.g16.backend.repository.AssignmentRepository
import edu.ntnu.idatt2105.g16.backend.repository.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@CrossOrigin
@RestController
@RequestMapping("/courses")
class CourseController {
    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    private lateinit var assignmentRepository: AssignmentRepository

    @GetMapping("/{id}")
    fun getCourseById(@PathVariable id: Long): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findCourseById(id)

        return if(optionalCourse.isPresent) {
            ResponseEntity.ok(CourseDTO(optionalCourse.get()))
        } else {
            ResponseEntity.badRequest().body("Course not found.")
        }
    }

    @GetMapping("/{id}/assignments")
    fun getAssignments(@PathVariable id:Long): ResponseEntity<Any> {
        val optionalAssignments = assignmentRepository.findAllByCourseId(id)

        return if (optionalAssignments.isPresent) {
            ResponseEntity.ok(optionalAssignments.get().map { it.ordinal })
        } else {
            ResponseEntity.badRequest().body("No assignments found")
        }
    }

    @GetMapping("/{id}/student/assignments/completed")
    fun getCompletedAssignments(principal: Principal, @PathVariable id: Long): ResponseEntity<Any> {
        val optionalCompleted = assignmentRepository.findAllByCourseIdAndUsers(id, principal.name)

        return if (optionalCompleted.isPresent) {
            ResponseEntity.ok(optionalCompleted.get().map { it.ordinal })
        } else {
            ResponseEntity.badRequest().body("No completed assignments found")
        }
    }
}
