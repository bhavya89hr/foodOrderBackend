package com.backend.foodorder.AuthenticationAndAuthorization

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.*
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val jwtUtil: JwtUtil,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<String> {
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user)
        return ResponseEntity.ok("User registered")
    }

    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): ResponseEntity<AuthResponse> {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(request.email)
        val jwt: String = jwtUtil.generateToken(userDetails.username).toString()
        return ResponseEntity.ok(AuthResponse(jwt))
    }
}
