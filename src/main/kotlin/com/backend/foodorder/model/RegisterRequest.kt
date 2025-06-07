package com.backend.foodorder.model

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
