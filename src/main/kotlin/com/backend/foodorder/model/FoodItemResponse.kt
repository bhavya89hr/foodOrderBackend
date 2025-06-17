package com.backend.foodorder.model

data class FoodItemResponse(
    val id: Long,
    val name: String,
    val price: Double,
    val photoUrl: String
)
