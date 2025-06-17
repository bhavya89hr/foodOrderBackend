package com.backend.foodorder.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.nio.file.Paths

@Configuration
class StaticResourceConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        val uploadPath = Paths.get(System.getProperty("user.home"), "food-uploads").toUri().toString()
        registry.addResourceHandler("/uploads/**")
            .addResourceLocations(uploadPath)
    }
}
