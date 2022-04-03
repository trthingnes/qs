package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.CourseDTO
import edu.ntnu.idatt2105.g16.backend.dto.UserDTO
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
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/courses/{id}")
class CourseController {
    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    private lateinit var assignmentRepository: AssignmentRepository

    @GetMapping
    fun getCourseById(@PathVariable id: Long): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findCourseById(id)

        return if(optionalCourse.isPresent) {
            ResponseEntity.ok(CourseDTO(optionalCourse.get()))
        } else {
            ResponseEntity.badRequest().body("Course not found.")
        }
    }

    @PutMapping
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

    @GetMapping("/students/")
    fun getStudentsByCourseId(@PathVariable id: Long): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findCourseById(id)

        return if(optionalCourse.isPresent) {
            ResponseEntity.ok(optionalCourse.get().students.map { UserDTO(it) })
        } else {
            ResponseEntity.badRequest().body("Course not found.")
        }
    }

    @GetMapping("/assistants/")
    fun getAssistantsByCourseId(@PathVariable id: Long): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findCourseById(id)

        return if(optionalCourse.isPresent) {
            ResponseEntity.ok(optionalCourse.get().assistants.map { UserDTO(it) })
        } else {
            ResponseEntity.badRequest().body("Course not found.")
        }
    }

    @GetMapping("/assignments")
    fun getAssignments(@PathVariable id:Long): ResponseEntity<Any> {
        val optionalAssignments = assignmentRepository.findAllByCourseId(id)

        return if (optionalAssignments.isPresent) {
            ResponseEntity.ok(optionalAssignments.get().map { it.ordinal })
        } else {
            ResponseEntity.badRequest().body("No assignments found")
        }
    }

    @GetMapping("/student/assignments/completed")
    fun getCompletedAssignments(principal: Principal, @PathVariable id: Long): ResponseEntity<Any> {
        val optionalAssignments = assignmentRepository.findByUsers_UsernameAndCourseId(principal.name, id)

        return if (optionalAssignments.isPresent) {
            ResponseEntity.ok(optionalAssignments.get().map { it.ordinal })
        } else {
            ResponseEntity.badRequest().body("No completed assignments found")
        }
    }
}
