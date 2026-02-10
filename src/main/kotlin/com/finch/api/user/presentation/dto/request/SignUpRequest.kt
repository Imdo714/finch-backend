package com.finch.api.user.presentation.dto.request

data class SignUpRequest(
    val email: String,
    val password: String,
    val name: String
)