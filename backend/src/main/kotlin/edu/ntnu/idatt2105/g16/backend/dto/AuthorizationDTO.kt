package edu.ntnu.idatt2105.g16.backend.dto

import java.io.Serializable

data class AuthorizationDTO(val username: String, val password: String) : Serializable
