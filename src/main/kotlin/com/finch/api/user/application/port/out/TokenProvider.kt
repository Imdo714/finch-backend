package com.finch.api.user.application.port.out

import jakarta.servlet.http.HttpServletRequest

interface TokenProvider {
    /** AccessToken 생성 */
    fun createAccessToken(userId: Long, role: String): String

    /** RefreshToken 생성 */
    fun createRefreshToken(userId: Long, role: String): String

    /** 헤더에서 토큰 값 추출 */
    fun extractBearerToken(request: HttpServletRequest): String?

}