package edu.ntnu.idatt2105.g16.backend.service

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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CourseService {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    private lateinit var assignmentRepository: AssignmentRepository

    fun addCourse(username: String, data: CourseDTO): Optional<Course> {
        val optionalUser = userRepository.findByUsername(username)
        if(optionalUser.isEmpty) {
            return Optional.empty()
        }

        val user = optionalUser.get()
        val course = courseRepository.save(Course(data))
        user.teacherCourses.add(course)
        userRepository.save(user)

        val assignments = mutableListOf<Assignment>()
        for(i in 1 .. data.numAssignments!!) {
            assignments.add(Assignment(AssignmentDTO(i, course)))
        }
        assignmentRepository.saveAll(assignments)

        return Optional.of(course)
    }

    fun updateCourseById(id: Long, data: CourseDTO): Optional<Course> {
        val optionalCourse = courseRepository.findCourseById(id)
        if (!optionalCourse.isPresent) {
            return Optional.empty()
        }

        val course = optionalCourse.get()
        course.update(data)

        return Optional.of(courseRepository.save(course))
    }

    fun getCourseById(id: Long): Optional<Course> {
        return courseRepository.findCourseById(id)
    }

    fun getUserStudentCourses(username: String): List<Course> {
        val optionalUser = userRepository.findByUsername(username)

        if (optionalUser.isEmpty) {
            return listOf()
        }

        return optionalUser.get().studentCourses
    }

    fun getUserAssistantCourses(username: String): List<Course> {
        val optionalUser = userRepository.findByUsername(username)

        if (optionalUser.isEmpty) {
            return listOf()
        }

        return optionalUser.get().assistantCourses
    }

    fun getUserTeacherCourses(username: String): List<Course> {
        val optionalUser = userRepository.findByUsername(username)

        if (optionalUser.isEmpty) {
            return listOf()
        }

        return optionalUser.get().teacherCourses.map { it }
    }

    fun addStudentToCourseById(id: Long, data: UserDTO): Boolean {
        val optionalUser = userRepository.findByUsername(data.username!!)
        val optionalCourse = courseRepository.findCourseById(id)

        if (optionalCourse.isEmpty) {
            return false
        }

        val course = optionalCourse.get()
        val user = if(optionalUser.isEmpty) { User(data) } else { optionalUser.get() }

        user.studentCourses.add(course)
        userRepository.save(user)

        course.students.add(user)
        courseRepository.save(course)

        return true
    }

    fun addAssistantToCourseById(id: Long, data: UserDTO): Boolean {
        val optionalUser = userRepository.findByUsername(data.username!!)
        val optionalCourse = courseRepository.findCourseById(id)

        if (optionalCourse.isEmpty) {
            return false
        }

        val course = optionalCourse.get()
        val user = if(optionalUser.isEmpty) { User(data) } else { optionalUser.get() }

        user.assistantCourses.add(course)
        user.role = Role.ASSISTANT
        userRepository.save(user)

        course.assistants.add(user)
        courseRepository.save(course)

        return true
    }

    fun removeStudentFromCourseById(id: Long, username: String): Boolean {
        val optionalUser = userRepository.findByUsername(username)
        val optionalCourse = courseRepository.findCourseById(id)

        if (optionalCourse.isEmpty || optionalUser.isEmpty) {
            return false
        }

        val user = optionalUser.get()
        val course = optionalCourse.get()

        user.studentCourses.remove(course)
        userRepository.save(user)

        course.students.remove(user)
        courseRepository.save(course)

        return true
    }

    fun removeAssistantFromCourseById(id: Long, username: String): Boolean {
        val optionalUser = userRepository.findByUsername(username)
        val optionalCourse = courseRepository.findCourseById(id)

        if (optionalCourse.isEmpty || optionalUser.isEmpty) {
            return false
        }

        val user = optionalUser.get()
        val course = optionalCourse.get()

        user.assistantCourses.remove(course)
        userRepository.save(user)

        course.assistants.remove(user)
        courseRepository.save(course)

        return true
    }
}