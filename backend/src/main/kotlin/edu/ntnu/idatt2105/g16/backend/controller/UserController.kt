package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.UserDTO
import edu.ntnu.idatt2105.g16.backend.repository.UserRepository
import edu.ntnu.idatt2105.g16.backend.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/user")
@Api(description = "Operations pertaining to creating, updating and getting users.")
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @PostMapping
    @PreAuthorize("hasAnyRole('TEACHER')")
    @ApiOperation("Creates a new user with the given info.")
    fun createUser(@RequestBody data: UserDTO): ResponseEntity<Any> {
        val user = userService.createUser(data)

        return ResponseEntity.ok(user)
    }

    @GetMapping
    @ApiOperation("Gets info about the current user.")
    fun getCurrentUser(principal: Principal): ResponseEntity<Any> {
        val optionalUser = userService.getUserByUsername(principal.name)

        return if (optionalUser.isPresent) {
            ResponseEntity.ok(UserDTO(optionalUser.get()))
        } else {
            ResponseEntity.badRequest().body("User not found.")
        }
    }

    @PutMapping
    @ApiOperation("Updates the current user with the given info")
    fun updateCurrentUser(principal: Principal, @RequestBody dto: UserDTO): ResponseEntity<Any> {
        val optionalUser = userService.getUserByUsername(principal.name)

        if (!optionalUser.isPresent) {
            return ResponseEntity.badRequest().body("User not found.")
        }

        val user = optionalUser.get()
        if (dto.password != null) {
            dto.password = BCryptPasswordEncoder().encode(dto.password)
        }
        user.update(dto)

        return ResponseEntity.ok(UserDTO(userService.saveUser(user)))
    }
}
