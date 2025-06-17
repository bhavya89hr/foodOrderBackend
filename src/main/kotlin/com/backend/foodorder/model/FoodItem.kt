package com.backend.foodorder.model

import jakarta.persistence.*

@Entity
data class FoodItem(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null,
    val name:String="",
    val price:Double=0.0,
    val photoUrl:String=""
)
