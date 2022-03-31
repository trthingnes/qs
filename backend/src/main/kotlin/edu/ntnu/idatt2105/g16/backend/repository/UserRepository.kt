package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
}
