package edu.ntnu.idatt2105.g16.backend.config

import edu.ntnu.idatt2105.g16.backend.security.AudienceValidator
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtDecoders
import org.springframework.security.oauth2.jwt.JwtValidators
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import java.util.Base64.getDecoder

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Value("\${auth0.audience}")
    val audience: String = ""

    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    val issuer: String = ""

    override fun configure(http: HttpSecurity?) {
        if (http == null) { return }

        http.cors().and() // Enable cross-origin.
            .csrf().disable() // Disable forgery protection.
            .headers().frameOptions().sameOrigin() // Needed for H2 console to work.
            .and()
            .authorizeRequests() // Requests to authorize will follow.
            //.anyRequest().permitAll() // ! Remove this to enforce security.
            .antMatchers(
                "/h2/**",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v2/api-docs/**"
            ).permitAll() // Allow access to debug tools.
            .anyRequest().authenticated()
            .and()
            .oauth2ResourceServer().jwt().decoder(getDecoder()) // Enable Auth0 with JWT.
    }

    fun getDecoder(): JwtDecoder {
        val withAudience = AudienceValidator(audience)
        val withIssuer = JwtValidators.createDefaultWithIssuer(issuer)
        val validator = DelegatingOAuth2TokenValidator(withAudience, withIssuer)

        val decoder = JwtDecoders.fromIssuerLocation(issuer) as NimbusJwtDecoder
        decoder.setJwtValidator(validator)

        return decoder
    }
}
