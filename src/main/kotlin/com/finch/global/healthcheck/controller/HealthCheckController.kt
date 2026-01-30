package com.finch.global.healthcheck.controller

import com.finch.global.common.domain.response.BaseResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping("/health")
    fun healthCheck(): BaseResponse<String> {
        return BaseResponse.ok("서비스 정상 동작")
    }

}