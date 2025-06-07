package com.backend.foodorder.service
import com.backend.foodorder.component.JwtUtil
import com.backend.foodorder.model.AuthResponse
import com.backend.foodorder.model.LoginRequest
import com.backend.foodorder.model.RegisterRequest
import com.backend.foodorder.model.Role
import com.backend.foodorder.repository.UserRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) {

    fun register(request: RegisterRequest): AuthResponse {
        val user = com.backend.foodorder.model.User(
            name = request.name,
            email = request.email,
            _password = passwordEncoder.encode(request.password),
            role = Role.USER
        )
        userRepository.save(user)
        val token = jwtUtil.generateToken(user)
        return AuthResponse(token)
    }

    fun login(request: LoginRequest): AuthResponse {
        val user = userRepository.findByEmail(request.email)
            .orElseThrow { UsernameNotFoundException("User not found") }

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw BadCredentialsException("Invalid password")
        }

        val token = jwtUtil.generateToken(user)
        return AuthResponse(token)
    }
}
