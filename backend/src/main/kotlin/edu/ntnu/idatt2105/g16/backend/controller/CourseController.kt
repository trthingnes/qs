package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.AssignmentDTO
import edu.ntnu.idatt2105.g16.backend.dto.CourseDTO
import edu.ntnu.idatt2105.g16.backend.dto.UserDTO
import edu.ntnu.idatt2105.g16.backend.entity.Assignment
import edu.ntnu.idatt2105.g16.backend.entity.Course
import edu.ntnu.idatt2105.g16.backend.entity.Role
import edu.ntnu.idatt2105.g16.backend.entity.User
import edu.ntnu.idatt2105.g16.backend.repository.AssignmentRepository
import edu.ntnu.idatt2105.g16.backend.repository.CourseRepository
import edu.ntnu.idatt2105.g16.backend.repository.UserRepository
import edu.ntnu.idatt2105.g16.backend.service.CourseService
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/courses")
@Api(description = "Operations pertaining to creating, updating and getting courses.")
class CourseController {
    @Autowired
    lateinit var courseService: CourseService

    @PostMapping
    @PreAuthorize("hasAnyRole('TEACHER')")
    @ApiOperation("Adds a new course with the given information.")
    fun addCourse(principal: Principal, @RequestBody data: CourseDTO): ResponseEntity<Any> {
        val courseOptional = courseService.addCourse(principal.name, data)

        return if(courseOptional.isPresent) {
            ResponseEntity.ok(CourseDTO(courseOptional.get()))
        } else {
            ResponseEntity.badRequest().body("Unable to add course.")
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER')")
    @ApiOperation("Updates a course by it's id.")
    fun updateCourseById(@PathVariable id: Long, @RequestBody data: CourseDTO): ResponseEntity<Any> {
        val courseOptional = courseService.updateCourseById(id, data)

        return if(courseOptional.isPresent) {
            ResponseEntity.ok(CourseDTO(courseOptional.get()))
        } else {
            ResponseEntity.badRequest().body("Unable to update course.")
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("Gets a course by it's id.")
    fun getCourseById(@PathVariable id: Long): ResponseEntity<Any> {
        val optionalCourse = courseService.getCourseById(id)

        return if(optionalCourse.isPresent) {
            ResponseEntity.ok(CourseDTO(optionalCourse.get()))
        } else {
            ResponseEntity.badRequest().body("Course not found.")
        }
    }

    @GetMapping("/student")
    @ApiOperation("Gets a list of the courses the current user is a student in.")
    fun getCurrentUserStudentCourses(principal: Principal): ResponseEntity<Any> {
        val courses = courseService.getUserStudentCourses(principal.name)

        return ResponseEntity.ok(courses.map { CourseDTO(it) })
    }

    @GetMapping("/assistant")
    @ApiOperation("Gets a list of the courses the current user is an assistant in.")
    fun getCurrentUserAssistantCourses(principal: Principal): ResponseEntity<Any> {
        val courses = courseService.getUserAssistantCourses(principal.name)

        return ResponseEntity.ok(courses.map { CourseDTO(it) })
    }

    @GetMapping("/teacher")
    @ApiOperation("Gets a list of the courses the current user is a teacher in.")
    fun getCurrentUserTeacherCourses(principal: Principal): ResponseEntity<Any> {
        val courses = courseService.getUserTeacherCourses(principal.name)

        return ResponseEntity.ok(courses.map { CourseDTO(it) })
    }

    @GetMapping("/{id}/students/")
    @PreAuthorize("hasAnyRole('ASSISTANT', 'TEACHER')")
    @ApiOperation("Gets a list of the students in the course with the given id.")
    fun getStudentsByCourseId(@PathVariable id: Long): ResponseEntity<Any> {
        val optionalCourse = courseService.getCourseById(id)

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
        val optionalCourse = courseService.getCourseById(id)

        return if(optionalCourse.isPresent) {
            ResponseEntity.ok(optionalCourse.get().assistants.map { UserDTO(it) })
        } else {
            ResponseEntity.badRequest().body("Course not found.")
        }
    }

    @PostMapping("/{id}/students")
    @PreAuthorize("hasAnyRole('TEACHER')")
    @ApiOperation("Adds a student user to the course with the given id.")
    fun addStudentByCourseId(@PathVariable id: Long, @RequestBody data: UserDTO): ResponseEntity<Any> {
        val success = courseService.addStudentToCourseById(id, data)

        return if (success) {
            ResponseEntity.ok("Successfully added student.")
        } else {
            ResponseEntity.badRequest().body("Unable to add student.")
        }
    }

    @PostMapping("/{id}/assistants")
    @PreAuthorize("hasAnyRole('TEACHER')")
    @ApiOperation("Adds an assistant user to the course with the given id.")
    fun addAssistantByCourseId(@PathVariable id: Long, @RequestBody data: UserDTO): ResponseEntity<Any> {
        val success = courseService.addAssistantToCourseById(id, data)

        return if (success) {
            ResponseEntity.ok("Successfully added assistant.")
        } else {
            ResponseEntity.badRequest().body("Unable to add assistant.")
        }
    }

    @DeleteMapping("/{id}/students/{username}")
    @PreAuthorize("hasAnyRole('TEACHER')")
    @ApiOperation("Deletes a student from the course with the given id.")
    fun removeStudentByCourseId(@PathVariable id: Long, @PathVariable username: String): ResponseEntity<Any> {
        val success = courseService.removeStudentFromCourseById(id, username)

        return if (success) {
            ResponseEntity.ok("Successfully removed student.")
        } else {
            ResponseEntity.badRequest().body("Unable to remove student.")
        }
    }

    @DeleteMapping("/{id}/assistants/{username}")
    @PreAuthorize("hasAnyRole('TEACHER')")
    @ApiOperation("Deletes an assistant from the course with the given id.")
    fun removeAssistantByCourseId(@PathVariable id: Long, @PathVariable username: String): ResponseEntity<Any> {
        val success = courseService.removeStudentFromCourseById(id, username)

        return if (success) {
            ResponseEntity.ok("Successfully removed assistant.")
        } else {
            ResponseEntity.badRequest().body("Unable to remove assistant.")
        }
    }
}
