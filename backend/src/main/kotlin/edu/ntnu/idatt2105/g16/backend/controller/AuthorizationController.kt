package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.security.JwtProvider
import edu.ntnu.idatt2105.g16.backend.dto.LoginDTO
import edu.ntnu.idatt2105.g16.backend.dto.TokenDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthorizationController {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var tokenProvider: JwtProvider

    @PostMapping("/token")
    fun authenticateUser(@Valid @RequestBody login: LoginDTO): ResponseEntity<TokenDTO> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(login.username, login.password)
        )

        SecurityContextHolder.getContext().authentication = authentication

        val token = tokenProvider.generateToken(authentication)

        return ResponseEntity.ok(TokenDTO(token))
    }

    @PreAuthorize("hasAnyRole('ROLE_STUDENT')")
    @GetMapping("/test/student")
    fun testStudentAuthentication(): ResponseEntity<String> {
        return ResponseEntity.ok("Successfully authorized as student.")
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    @GetMapping("/test/teacher")
    fun testTeacherAuthentication(): ResponseEntity<String> {
        return ResponseEntity.ok("Successfully authorized as teacher.")
    }
}