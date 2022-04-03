package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.AuthorizationDTO
import edu.ntnu.idatt2105.g16.backend.dto.TokenDTO
import edu.ntnu.idatt2105.g16.backend.security.JwtProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthorizationController {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var tokenProvider: JwtProvider

    @PostMapping("/token")
    fun authenticateUser(@Valid @RequestBody login: AuthorizationDTO): ResponseEntity<TokenDTO> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(login.username, login.password)
        )

        SecurityContextHolder.getContext().authentication = authentication

        val token = tokenProvider.generateToken(authentication)

        return ResponseEntity.ok(TokenDTO(token))
    }

    @GetMapping("/test/student")
    @PreAuthorize("hasAnyRole('STUDENT')")
    fun testStudentAuthentication(user: Principal): ResponseEntity<String> {
        return ResponseEntity.ok("Successfully authorized as student ${user.name}.")
    }

    @GetMapping("/test/assistant")
    @PreAuthorize("hasAnyRole('ASSISTANT')")
    fun testAssistantAuthentication(user: Principal): ResponseEntity<String> {
        return ResponseEntity.ok("Successfully authorized as assistant ${user.name}.")
    }

    @GetMapping("/test/teacher")
    @PreAuthorize("hasAnyRole('TEACHER')")
    fun testTeacherAuthentication(user: Principal): ResponseEntity<String> {
        return ResponseEntity.ok("Successfully authorized as teacher ${user.name}.")
    }
}
