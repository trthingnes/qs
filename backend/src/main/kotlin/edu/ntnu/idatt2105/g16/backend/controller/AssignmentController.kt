package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.AssignmentDTO
import edu.ntnu.idatt2105.g16.backend.dto.UserDTO
import edu.ntnu.idatt2105.g16.backend.repository.AssignmentRepository
import edu.ntnu.idatt2105.g16.backend.repository.UserRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("courses/{id}/assignments")
@Api(description = "Operations pertaining to creating, updating and getting assignments.")
class AssignmentController {
    @Autowired
    private lateinit var assignmentRepository: AssignmentRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @GetMapping
    @ApiOperation("Gets a list of all the assignments of the course with the given id.")
    fun getCourseAssignments(@PathVariable id: Long): ResponseEntity<Any> {
        val optionalAssignments = assignmentRepository.findAllByCourseId(id)

        return if (optionalAssignments.isPresent) {
            ResponseEntity.ok(optionalAssignments.get().map { it.ordinal })
        } else {
            ResponseEntity.badRequest().body("No assignments found.")
        }
    }

    @GetMapping("/completed")
    @ApiOperation("Gets all of the current users completed assignments in the course with id given.")
    fun getCurrentUserCompletedAssignments(principal: Principal, @PathVariable id: Long): ResponseEntity<Any> {
        val optionalAssignments = assignmentRepository.findByUsers_UsernameAndCourseId(principal.name, id)

        return if (optionalAssignments.isPresent) {
            ResponseEntity.ok(optionalAssignments.get().map { it.ordinal })
        } else {
            ResponseEntity.badRequest().body("No completed assignments found")
        }
    }

    @PostMapping("/{ordinal}")
    @ApiOperation("Add a user to the list with the users who completed a specific assignment")
    fun postUserCompletedAssignment(@PathVariable id: Long, @PathVariable ordinal: Int, @RequestBody user: UserDTO): ResponseEntity<Any> {
        val receivedUser = user.username
        var username = "notfound"
        if (receivedUser != null) {
            username = receivedUser
        }
        val optionalAssignment = assignmentRepository.findByCourseIdAndOrdinal(id, ordinal)
        val optionalUser = userRepository.findByUsername(username)

        return if (optionalAssignment.isPresent && optionalUser.isPresent) {
            val assignment = optionalAssignment.get()
            assignment.users.add(optionalUser.get())
            assignmentRepository.save(assignment)

            ResponseEntity.ok(AssignmentDTO(assignment))
        } else {
            ResponseEntity.badRequest().body("Could not find assignment or user")
        }
    }
}
