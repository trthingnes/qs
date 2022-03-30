package edu.ntnu.idatt2105.g16.backend.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter : OncePerRequestFilter() {
    @Autowired
    private lateinit var tokenProvider: JwtProvider

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val token = getJwtFromRequest(request)

            if (token != null) {
                if (!tokenProvider.validateToken(token)) {
                    throw IllegalStateException("Unable to validate token")
                }

                val username = tokenProvider.getUsernameFromToken(token)
                val userDetails = userDetailsService.loadUserByUsername(username)
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            logger.error("Could not set user authentication in security context: ${e.message}")
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearer = request.getHeader("Authorization") ?: return null

        if (bearer.isNotBlank() && bearer.startsWith("Bearer ")) {
            return bearer.substring(7, bearer.length)
        }

        return null
    }
}
