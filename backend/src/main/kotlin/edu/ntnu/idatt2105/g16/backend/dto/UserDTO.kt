package edu.ntnu.idatt2105.g16.backend.dto

import edu.ntnu.idatt2105.g16.backend.entity.Role

data class UserDTO(
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: Role
)