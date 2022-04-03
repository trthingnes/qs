package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.CourseDTO
import edu.ntnu.idatt2105.g16.backend.dto.QueueEntryDTO
import edu.ntnu.idatt2105.g16.backend.dto.UserDTO
import edu.ntnu.idatt2105.g16.backend.entity.QueueEntry
import edu.ntnu.idatt2105.g16.backend.repository.CourseRepository
import edu.ntnu.idatt2105.g16.backend.repository.QueueEntryRepository
import edu.ntnu.idatt2105.g16.backend.repository.UserRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/courses")
@Api(description = "Operations pertaining to creating, updating and getting courses.")
class CourseController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @GetMapping("/student")
    @ApiOperation("Gets a list of the courses the current user is a student in.")
    fun getCurrentUserStudentCourses(principal: Principal): ResponseEntity<Any> {
        val optionalUser = userRepository.findByUsername(principal.name)

        return if (optionalUser.isPresent) {
            ResponseEntity.ok(optionalUser.get().studentCourses.map { CourseDTO(it) })
        } else {
            ResponseEntity.badRequest().body("User not found.")
        }
    }

    @GetMapping("/assistant")
    @ApiOperation("Gets a list of the courses the current user is an assistant in.")
    fun getCurrentUserAssistantCourses(principal: Principal): ResponseEntity<Any> {
        val optionalUser = userRepository.findByUsername(principal.name)

        return if (optionalUser.isPresent) {
            ResponseEntity.ok(optionalUser.get().assistantCourses.map { CourseDTO(it) })
        } else {
            ResponseEntity.badRequest().body("User not found.")
        }
    }

    @GetMapping("/teacher")
    @ApiOperation("Gets a list of the courses the current user is a teacher in.")
    fun getCurrentUserTeacherCourses(principal: Principal): ResponseEntity<Any> {
        val optionalUser = userRepository.findByUsername(principal.name)

        return if (optionalUser.isPresent) {
            ResponseEntity.ok(optionalUser.get().teacherCourses.map { CourseDTO(it) })
        } else {
            ResponseEntity.badRequest().body("User not found.")
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("Gets a course by it's id.")
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
    @ApiOperation("Updates a course by it's id.")
    fun updateCourseById(@PathVariable id: Long, @RequestBody dto: CourseDTO): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findCourseById(id)
        if (!optionalCourse.isPresent) {
            return ResponseEntity.badRequest().body("Course not found.")
        }

        val course = optionalCourse.get()
        course.update(dto)

        return ResponseEntity.ok(CourseDTO(courseRepository.save(course)))
    }

    @GetMapping("/{id}/students/")
    @PreAuthorize("hasAnyRole('ASSISTANT', 'TEACHER')")
    @ApiOperation("Gets a list of the students in the course with the given id.")
    fun getStudentsByCourseId(@PathVariable id: Long): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findCourseById(id)

        return if(optionalCourse.isPresent) {
            ResponseEntity.ok(optionalCourse.get().students.map { UserDTO(it) })
        } else {
            ResponseEntity.badRequest().body("Course not found.")
        }
    }

    @GetMapping("/{id}/assistants")
    @PreAuthorize("hasAnyRole('ASSISTANT', 'TEACHER')")
    @ApiOperation("Gets a list of the assistants in the course with the given id.")
    fun getAssistantsByCourseId(@PathVariable id: Long): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findCourseById(id)

        return if(optionalCourse.isPresent) {
            ResponseEntity.ok(optionalCourse.get().assistants.map { UserDTO(it) })
        } else {
            ResponseEntity.badRequest().body("Course not found.")
        }
    }
}
