package com.finch.api.user.infrastructure.social.google.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.finch.api.user.domain.service.SocialUserInfoDto

data class GoogleUserInfoDto(
    val sub: String,

    val name: String?,

    override val email: String,

    @get:JsonProperty("email_verified")
    val emailVerified: Boolean?,

    val picture: String?
) : SocialUserInfoDto {

    override val providerId: String
        get() = sub
}