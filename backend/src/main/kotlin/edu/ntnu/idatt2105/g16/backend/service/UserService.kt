package edu.ntnu.idatt2105.g16.backend.service

import edu.ntnu.idatt2105.g16.backend.dto.UserDTO
import edu.ntnu.idatt2105.g16.backend.entity.User
import edu.ntnu.idatt2105.g16.backend.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(var userRepository: UserRepository) {
    fun createUser(data: UserDTO): User {
        val user = User(data)

        // Hash password with BCrypt before passing it along.
        user.password = BCryptPasswordEncoder().encode(user.password)

        return userRepository.save(user)
    }

    fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    fun getUserByUsername(username: String): Optional<User> {
        return userRepository.findByUsername(username)
    }
}