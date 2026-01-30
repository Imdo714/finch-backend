package com.finch.global.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class CorsConfig {

    @Value("\${cors.origin.local:}")
    private lateinit var localOrigin: String

    @Value("\${cors.origin.development:}")
    private lateinit var developmentOrigin: String

    @Value("\${cors.origin.production:}")
    private lateinit var productionOrigin: String

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()

        config.allowCredentials = true

        // 유효한 도메인만 필터링하여 리스트 생성
        val originList = listOf(localOrigin, developmentOrigin, productionOrigin)
            .filter { it.isNotBlank() }

        config.allowedOriginPatterns = originList
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        config.maxAge = 3600L

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }

}