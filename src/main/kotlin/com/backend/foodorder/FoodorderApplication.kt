package com.backend.foodorder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FoodorderApplication

fun main(args: Array<String>) {
	runApplication<FoodorderApplication>(*args)
}
