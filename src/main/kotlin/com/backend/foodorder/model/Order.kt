package com.backend.foodorder.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "orders")

data class Order(
    @Id @GeneratedValue val id: Long? = null,
    val userId: String,
    val items: String, // JSON or comma-separated item IDs
    val total: Double,
    val status: String = "PLACED",
    val createdAt: LocalDateTime = LocalDateTime.now()
)
