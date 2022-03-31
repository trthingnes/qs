package edu.ntnu.idatt2105.g16.backend.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider {
    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.expirationMs}")
    private var expirationMs: Int = 0

    fun generateToken(auth: Authentication): String {
        val key = Keys.hmacShaKeyFor(secret.toByteArray())
        val principal = auth.principal as UserPrincipal

        val now = Date()
        val expiry = Date(now.time + expirationMs)

        return Jwts.builder()
            .setSubject(principal.username)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .signWith(key)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        val key = Keys.hmacShaKeyFor(secret.toByteArray())
        val parser = Jwts.parserBuilder().setSigningKey(key).build()

        return try {
            parser.parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getUsernameFromToken(token: String): String {
        val key = Keys.hmacShaKeyFor(secret.toByteArray())
        val parser = Jwts.parserBuilder().setSigningKey(key).build()
        val claims = parser.parseClaimsJws(token).body

        return claims.subject
    }
}
