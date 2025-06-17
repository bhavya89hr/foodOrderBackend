package com.backend.foodorder.controller

import com.backend.foodorder.model.AddFoodItemRequest
import com.backend.foodorder.model.FoodItemResponse
import com.backend.foodorder.service.FoodItemService
import org.springframework.web.bind.annotation.*


import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/food")
class FoodItemController(
    private val service: FoodItemService
) {

    // Accepts multipart form data: name, price, photo
    @PostMapping(consumes = ["multipart/form-data"])
    fun addFoodItem(
        @RequestParam name: String,
        @RequestParam price: Double,
        @RequestParam photo: MultipartFile
    ): FoodItemResponse {
        return service.addFoodItemWithPhoto(name, price, photo)
    }

    @GetMapping
    fun listFoodItems(): List<FoodItemResponse> {
        return service.listAllFoodItems()
    }
}
