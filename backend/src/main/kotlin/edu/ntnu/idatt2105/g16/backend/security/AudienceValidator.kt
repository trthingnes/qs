package edu.ntnu.idatt2105.g16.backend.security

import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2ErrorCodes
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
import org.springframework.security.oauth2.jwt.Jwt

class AudienceValidator(private val audience: String) : OAuth2TokenValidator<Jwt> {
    init {
        if(audience.isBlank()) {
            throw IllegalStateException("Audience cannot be blank")
        }
    }

    override fun validate(token: Jwt?): OAuth2TokenValidatorResult {
        if(token == null || !token.audience.contains(audience)) {
            return OAuth2TokenValidatorResult.failure(OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN))
        }

        return OAuth2TokenValidatorResult.success()
    }
}