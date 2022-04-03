package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.AuthorizationDTO
import edu.ntnu.idatt2105.g16.backend.dto.TokenDTO
import edu.ntnu.idatt2105.g16.backend.service.AuthorizationService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
@Api(description = "Operations pertaining to allowing user access to the API.")
class AuthorizationController {
    @Autowired
    lateinit var authorizationService: AuthorizationService

    @PostMapping("/token")
    @ApiOperation("Gets an access token with the given username and password.")
    fun getTokenFromCredentials(@Valid @RequestBody login: AuthorizationDTO): ResponseEntity<TokenDTO> {
        val token = authorizationService.getTokenFromCredentials(login.username, login.password)

        return ResponseEntity.ok(TokenDTO(token))
    }
}
