package com.backend.foodorder.model

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile


data class AddFoodItemRequest(
    val name: String,
    val price: Double,
    val photoUrl: String
)
