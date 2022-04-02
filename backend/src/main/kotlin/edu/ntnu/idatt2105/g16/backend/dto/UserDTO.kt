package edu.ntnu.idatt2105.g16.backend.dto

import edu.ntnu.idatt2105.g16.backend.entity.Role
import edu.ntnu.idatt2105.g16.backend.entity.User

data class UserDTO(
    val username: String?,
    var password: String?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val role: Role?
) {
    constructor(u: User): this(u.username, u.password, u.firstName, u.lastName, u.email, u.role)
}
