package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.CourseDTO
import edu.ntnu.idatt2105.g16.backend.dto.QueueEntryDTO
import edu.ntnu.idatt2105.g16.backend.dto.UserDTO
import edu.ntnu.idatt2105.g16.backend.entity.QueueEntry
import edu.ntnu.idatt2105.g16.backend.repository.AssignmentRepository
import edu.ntnu.idatt2105.g16.backend.repository.CourseRepository
import edu.ntnu.idatt2105.g16.backend.repository.UserRepository
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
class CourseController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    private lateinit var assignmentRepository: AssignmentRepository

    @GetMapping("/student")
    fun getCurrentUserStudentCourses(principal: Principal): ResponseEntity<Any> {
        val optionalUser = userRepository.findByUsername(principal.name)

        return if (optionalUser.isPresent) {
            ResponseEntity.ok(optionalUser.get().studentCourses.map { CourseDTO(it) })
        } else {
            ResponseEntity.badRequest().body("User not found.")
        }
    }

    @GetMapping("/assistant")
    @PreAuthorize("hasAnyRole('ASSISTANT', 'TEACHER')")
    fun getCurrentUserAssistantCourses(principal: Principal): ResponseEntity<Any> {
        val optionalUser = userRepository.findByUsername(principal.name)

        return if (optionalUser.isPresent) {
            ResponseEntity.ok(optionalUser.get().assistantCourses.map { CourseDTO(it) })
        } else {
            ResponseEntity.badRequest().body("User not found.")
        }
    }

    @GetMapping("/teacher")
    @PreAuthorize("hasAnyRole('TEACHER')")
    fun getCurrentUserTeacherCourses(principal: Principal): ResponseEntity<Any> {
        val optionalUser = userRepository.findByUsername(principal.name)

        return if (optionalUser.isPresent) {
            ResponseEntity.ok(optionalUser.get().teacherCourses.map { CourseDTO(it) })
        } else {
            ResponseEntity.badRequest().body("User not found.")
        }
    }

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

    @GetMapping("/{id}/students/")
    @PreAuthorize("hasAnyRole('ASSISTANT', 'TEACHER')")
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
    fun getAssistantsByCourseId(@PathVariable id: Long): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findCourseById(id)

        return if(optionalCourse.isPresent) {
            ResponseEntity.ok(optionalCourse.get().assistants.map { UserDTO(it) })
        } else {
            ResponseEntity.badRequest().body("Course not found.")
        }
    }

    @GetMapping("/{id}/assignments")
    fun getCourseAssignments(@PathVariable id:Long): ResponseEntity<Any> {
        val optionalAssignments = assignmentRepository.findAllByCourseId(id)

        return if (optionalAssignments.isPresent) {
            ResponseEntity.ok(optionalAssignments.get().map { it.ordinal })
        } else {
            ResponseEntity.badRequest().body("No assignments found")
        }
    }

    @GetMapping("/{id}/assignments/completed")
    fun getCurrentUserCompletedAssignments(principal: Principal, @PathVariable id: Long): ResponseEntity<Any> {
        val optionalAssignments = assignmentRepository.findByUsers_UsernameAndCourseId(principal.name, id)

        return if (optionalAssignments.isPresent) {
            ResponseEntity.ok(optionalAssignments.get().map { it.ordinal })
        } else {
            ResponseEntity.badRequest().body("No completed assignments found")
        }
    }

    @PostMapping("{id}/queue")
    fun postQueueEntry(@PathVariable id: Long, @RequestBody data: QueueEntryDTO): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findById(id)

        return if (optionalCourse.isPresent) {
            val course = optionalCourse.get()
            val queueEntry = queueEntryRepository.save(QueueEntry(data))
            course.queueEntries.add(queueEntry)
            return ResponseEntity.ok(courseRepository.save(course))
        } else {
            ResponseEntity.badRequest().body("Could not find course")
        }

    }
}
