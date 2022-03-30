package edu.ntnu.idatt2105.g16.backend.service

import edu.ntnu.idatt2105.g16.backend.repository.UserRepository
import edu.ntnu.idatt2105.g16.backend.security.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserDetailsService : UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username).orElseThrow {
            UsernameNotFoundException("User not found")
        }

        return UserPrincipal.mapFromUser(user)
    }
}
