package com.backend.foodorder.service

import com.backend.foodorder.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Bean
fun userDetailsService(userRepository: UserRepository): UserDetailsService {
    return UserDetailsService { email ->
        userRepository.findByEmail(email)
            .orElseThrow { UsernameNotFoundException("User not found with email: $email") }
    }
}
