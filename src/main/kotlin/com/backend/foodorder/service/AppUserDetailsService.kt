package com.backend.foodorder.service


import com.backend.foodorder.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AppUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findByEmail(email)
            .orElseThrow { UsernameNotFoundException("User not found with email: $email") }
    }
}
