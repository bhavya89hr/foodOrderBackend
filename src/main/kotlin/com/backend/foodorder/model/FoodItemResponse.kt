package com.backend.foodorder.model

data class FoodItemResponse(
    val id: String?,
    val name: String,
    val price: Double,
    val imageUrl: String
)