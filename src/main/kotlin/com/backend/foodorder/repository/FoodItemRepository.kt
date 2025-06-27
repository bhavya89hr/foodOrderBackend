package com.backend.foodorder.repository

import com.backend.foodorder.model.FoodItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.mongodb.repository.MongoRepository

interface FoodItemRepository : MongoRepository<FoodItem, String>
