package edu.ntnu.idatt2105.g16.backend.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig: WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        if (http == null) { return }

        http.cors().and() // Enable cross-origin.
            .csrf().disable() // Disable forgery protection.
            .headers()
            .frameOptions().sameOrigin() // Set frame-options header.
            .and()
            .authorizeRequests() // Requests to authorize will follow.
            .antMatchers("/h2/**").permitAll() // Allow all H2 console access.
            .anyRequest().permitAll() // ! Remove this to enforce security.
    }
}