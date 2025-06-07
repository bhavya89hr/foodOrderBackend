package com.backend.foodorder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.backend.foodorder"])

class FoodorderApplication

fun main(args: Array<String>) {
	runApplication<FoodorderApplication>(*args)
}
