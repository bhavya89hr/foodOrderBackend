package com.backend.foodorder.controller

import com.backend.foodorder.model.AuthResponse
import com.backend.foodorder.model.LoginRequest
import com.backend.foodorder.model.RegisterRequest
import com.backend.foodorder.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.register(request))
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.login(request))
    }
}
