package edu.ntnu.idatt2105.g16.backend.service

import edu.ntnu.idatt2105.g16.backend.security.JwtProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthorizationService {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var tokenProvider: JwtProvider

    fun getTokenFromCredentials(username: String, password: String): String {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(username, password)
        )

        SecurityContextHolder.getContext().authentication = authentication

        return tokenProvider.generateToken(authentication)
    }
}
