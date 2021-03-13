package com.dsm.springsecuritystudy

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class TokenProvider(
    @Value("\${TOKEN:spring-security-love}")
    private val secretKey: String,
    private val userDetailsService: UserDetailsService,
) {
    private val encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())

    fun createToken(accountId: String, roles: List<String>, millisecondOfExpirationTime: Long) =
        Jwts.builder()
            .setSubject(accountId)
            .claim("roles", roles)
            .setExpiration(Date(System.currentTimeMillis() + millisecondOfExpirationTime))
            .signWith(SignatureAlgorithm.HS384, encodedSecretKey)
            .compact()

    fun getData(token: String): String =
        Jwts.parser()
            .setSigningKey(encodedSecretKey)
            .parseClaimsJws(token)
            .body
            .subject

    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getData(token))
        return UsernamePasswordAuthenticationToken(
            userDetails,
            "",
            userDetails.authorities,
        )
    }

    fun extractToken(request: HttpServletRequest) = request.getHeader("Authorization")

    fun validateToken(token: String) =
        try {
            val currentTime = Date()
            val expirationTime = Jwts.parser()
                .setSigningKey(encodedSecretKey)
                .parseClaimsJws(token)
                .body
                .expiration
            expirationTime.after(currentTime)
        } catch (e: Exception) { false }
}