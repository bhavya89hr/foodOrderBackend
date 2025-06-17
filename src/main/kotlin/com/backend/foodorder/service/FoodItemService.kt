package com.backend.foodorder.service

import com.backend.foodorder.model.FoodItem
import com.backend.foodorder.model.FoodItemResponse
import com.backend.foodorder.repository.FoodItemRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*



@Service
class FoodItemService(
    private val foodItemRepository: FoodItemRepository
) {

    fun addFoodItemWithPhoto(name: String, price: Double, photo: MultipartFile): FoodItemResponse {
        // 1. Create uploads directory inside user's home folder
        val uploadsDir = File(System.getProperty("user.home"), "food-uploads")
        if (!uploadsDir.exists()) uploadsDir.mkdirs()

        // 2. Save file to the directory
        val fileName = "${UUID.randomUUID()}_${photo.originalFilename}"
        val file = File(uploadsDir, fileName)
        photo.transferTo(file)

        // 3. Save food item to the database
        val saved = foodItemRepository.save(
            FoodItem(
                name = name,
                price = price,
                photoUrl = "/uploads/$fileName"
            )
        )

        return FoodItemResponse(saved.id!!, saved.name, saved.price, saved.photoUrl)
    }

    fun listAllFoodItems(): List<FoodItemResponse> {
        return foodItemRepository.findAll().map {
            FoodItemResponse(it.id!!, it.name, it.price, it.photoUrl)
        }
    }
}
