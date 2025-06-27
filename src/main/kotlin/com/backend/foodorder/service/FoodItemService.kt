package com.backend.foodorder.service

import com.backend.foodorder.model.FoodItem
import com.backend.foodorder.model.FoodItemResponse
import com.backend.foodorder.repository.FoodItemRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*



@Service
class FoodItemService(
    private val repo: FoodItemRepository
) {

    fun addFoodItemWithPhoto(name: String, price: Double, photo: MultipartFile): FoodItemResponse {
        val imageUrl = saveImage(photo) // You implement this
        val savedItem = repo.save(
            FoodItem(
                name = name,
                price = price,
                imageUrl = imageUrl
            )
        )
        return toResponse(savedItem)
    }

    fun listAllFoodItems(): List<FoodItemResponse> {
        return repo.findAll().map { toResponse(it) }
    }

    private fun toResponse(item: FoodItem): FoodItemResponse {
        return FoodItemResponse(
            id = item.id,
            name = item.name,
            price = item.price,
            imageUrl = item.imageUrl
        )
    }

    private fun saveImage(photo: MultipartFile): String {
        // Save to local / upload to Firebase, then return the URL
        val fileName = UUID.randomUUID().toString() + "_" + photo.originalFilename
        val uploadDir = Paths.get("uploads")
        Files.createDirectories(uploadDir)
        val path = uploadDir.resolve(fileName)
        photo.transferTo(path)

        // In real apps, return public URL (Firebase S3 etc.)
        return "http://localhost:8080/uploads/$fileName"
    }
}
