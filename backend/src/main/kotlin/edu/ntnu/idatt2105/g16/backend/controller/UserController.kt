package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.UserDTO
import edu.ntnu.idatt2105.g16.backend.entity.User
import edu.ntnu.idatt2105.g16.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @PostMapping("/")
    // @PreAuthorize("hasAnyRole('TEACHER')")
    fun createUser(@RequestBody data: UserDTO): ResponseEntity<Any> {
        val user = User(data)

        // Hash password with BCrypt before passing it along.
        user.password = BCryptPasswordEncoder().encode(user.password)

        return ResponseEntity.ok(userRepository.save(user))
    }

    @GetMapping("/")
    fun getCurrentUser(principal: Principal): ResponseEntity<Any> {
        val user = userRepository.findByUsername(principal.name)

        return if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.badRequest().body("User not found.")
        }
    }
}
