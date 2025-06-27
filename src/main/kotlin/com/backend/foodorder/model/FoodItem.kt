package com.backend.foodorder.model

import jakarta.persistence.*
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "food_items")
data class FoodItem(
    @Id val id: String? = null,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
