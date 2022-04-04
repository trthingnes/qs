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
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    private lateinit var assignmentRepository: AssignmentRepository

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

    @PostMapping
    @PreAuthorize("hasAnyRole('TEACHER')")
    @ApiOperation("Adds a new course with the given information.")
    fun addCourse(principal: Principal, @RequestBody dto: CourseDTO): ResponseEntity<Any> {
        val optionalUser = userRepository.findByUsername(principal.name)

        if(optionalUser.isEmpty) {
            return ResponseEntity.badRequest().body("Could not find user.")
        }

        val user = optionalUser.get()
        val course = courseRepository.save(Course(dto))

        val assignments = mutableListOf<Assignment>()
        for(i in 1 .. dto.numAssignments!!) {
            assignments.add(Assignment(AssignmentDTO(i, course)))
        }
        assignmentRepository.saveAll(assignments)

        user.teacherCourses.add(course)
        userRepository.save(user)

        return ResponseEntity.ok(CourseDTO(course))
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

    @PostMapping("/{id}/students")
    @PreAuthorize("hasAnyRole('TEACHER')")
    @ApiOperation("Adds a student user to the course with the given id.")
    fun addStudentByCourseId(@PathVariable id: Long, @RequestBody data: UserDTO): ResponseEntity<Any> {
        val optionalUser = userRepository.findByUsername(data.username!!)
        val optionalCourse = courseRepository.findCourseById(id)

        if (optionalCourse.isEmpty) {
            return ResponseEntity.badRequest().body("Course not found.")
        }

        val course = optionalCourse.get()
        val user = if(optionalUser.isEmpty) { userRepository.save(User(data)) } else { optionalUser.get() }

        user.studentCourses.add(course)
        userRepository.save(user)

        course.students.add(user)
        courseRepository.save(course)

        return ResponseEntity.ok("Successfully added student to class.")
    }

    @PostMapping("/{id}/assistants")
    @PreAuthorize("hasAnyRole('TEACHER')")
    @ApiOperation("Adds an assistant user to the course with the given id.")
    fun addAssistantByCourseId(@PathVariable id: Long, @RequestBody data: UserDTO): ResponseEntity<Any> {
        val optionalUser = userRepository.findByUsername(data.username!!)
        val optionalCourse = courseRepository.findCourseById(id)

        if (optionalCourse.isEmpty) {
            return ResponseEntity.badRequest().body("Course not found.")
        }

        val course = optionalCourse.get()
        val user = if(optionalUser.isEmpty) {
            data.password = BCryptPasswordEncoder().encode(data.password)
            userRepository.save(User(data))
        } else { optionalUser.get() }

        user.role = Role.ASSISTANT
        user.assistantCourses.add(course)
        userRepository.save(user)

        course.assistants.add(user)
        courseRepository.save(course)

        return ResponseEntity.ok("Successfully added assistant to class.")
    }

    @DeleteMapping("/{id}/students/{username}")
    @PreAuthorize("hasAnyRole('TEACHER')")
    @ApiOperation("Deletes a student from the course with the given id.")
    fun removeStudentByCourseId(@PathVariable id: Long, @PathVariable username: String): ResponseEntity<Any> {
        val optionalUser = userRepository.findByUsername(username)
        val optionalCourse = courseRepository.findCourseById(id)

        if (optionalCourse.isEmpty || optionalUser.isEmpty) {
            return ResponseEntity.badRequest().body("Course or user not found.")
        }

        val user = optionalUser.get()
        val course = optionalCourse.get()

        user.studentCourses.remove(course)
        userRepository.save(user)

        course.students.remove(user)
        courseRepository.save(course)

        return ResponseEntity.ok("Student successfully removed.")
    }

    @DeleteMapping("/{id}/assistants/{username}")
    @PreAuthorize("hasAnyRole('TEACHER')")
    @ApiOperation("Deletes an assistant from the course with the given id.")
    fun removeAssistantByCourseId(@PathVariable id: Long, @PathVariable username: String): ResponseEntity<Any> {
        val optionalUser = userRepository.findByUsername(username)
        val optionalCourse = courseRepository.findCourseById(id)

        if (optionalCourse.isEmpty || optionalUser.isEmpty) {
            return ResponseEntity.badRequest().body("Course or user not found.")
        }

        val user = optionalUser.get()
        val course = optionalCourse.get()

        user.assistantCourses.remove(course)
        userRepository.save(user)

        course.assistants.remove(user)
        courseRepository.save(course)

        return ResponseEntity.ok("Assistant successfully removed.")
    }
}
