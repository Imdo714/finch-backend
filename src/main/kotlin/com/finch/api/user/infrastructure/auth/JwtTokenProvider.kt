package com.finch.api.user.infrastructure.auth

import com.finch.api.user.application.port.out.TokenProvider
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}")
    private val secretKey: String,

    @Value("\${jwt.access-token-expiration}")
    private val accessTokenExpiration: Long,

    @Value("\${jwt.refresh-token-expiration}")
    private val RefreshTokenExpiration: Long

): TokenProvider {

    override fun createAccessToken(userId: Long, role: String): String {
        val claims = Jwts.claims()
            .subject(userId.toString())
            .add("role", role)
            .build();
        return createToken(claims, accessTokenExpiration)
    }

    private fun createToken(claims: Claims, expiration: Long): String {
        val now = Date()
        val expiry = Date(now.time + expiration)

        return Jwts.builder()
            .claims(claims)
            .issuedAt(now)
            .expiration(expiry)
            .signWith(getSigningKey(), Jwts.SIG.HS256)
            .compact()
    }

    private fun getSigningKey(): SecretKey =
        Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

}