package edu.ntnu.idatt2105.g16.backend

import edu.ntnu.idatt2105.g16.backend.controller.AuthorizationController
import edu.ntnu.idatt2105.g16.backend.security.JwtProvider
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension::class)
class AuthorizationControllerTest {
    @Mock
    private lateinit var authenticationManager: AuthenticationManager

    @Mock
    private lateinit var tokenProvider: JwtProvider

    @InjectMocks
    private lateinit var authorizationController: AuthorizationController

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @DisplayName("Token is successfully generated given correct username and password.")
    internal fun testCorrectUsernameAndPasswordGivesToken() {
        TODO("Not yet implemented")
    }
}