package edu.ntnu.idatt2105.g16.backend.security

import edu.ntnu.idatt2105.g16.backend.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal : UserDetails {
    private lateinit var authorities: Collection<GrantedAuthority>
    private lateinit var username: String
    private lateinit var password: String

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities
    override fun getUsername(): String = username
    override fun getPassword(): String = password

    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true

    companion object {
        fun mapFromUser(user: User): UserPrincipal {
            val principal = UserPrincipal()

            with(principal) {
                username = user.username
                password = user.password
                authorities = listOf(SimpleGrantedAuthority("ROLE_${user.role.name}"))
            }

            return principal
        }
    }
}