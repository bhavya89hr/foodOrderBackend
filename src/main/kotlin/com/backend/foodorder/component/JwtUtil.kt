package com.backend.foodorder.component


import com.backend.foodorder.model.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtUtil {

    private val secret = "3d9475383ef96234410a21f4d932facfe36913c00e224bce1da22d0a5083696efe33ee8feb5ec550722775033302f2f744689b13604c738d1d6034d5c113ec38" // should be Base64-safe
    private val expirationMs = 24 * 60 * 60 * 1000 // 1 day

    fun generateToken(user: User): String {
        val now = Date()
        val expiryDate = Date(now.time + expirationMs)

        val claims = Jwts.claims().setSubject(user.email)
        claims["role"] = user.role.name

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(
                SignatureAlgorithm.HS256,
                Base64.getDecoder().decode(secret)
            )
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = getClaims(token)
            !claims.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun getUsername(token: String): String {
        return getClaims(token).subject
    }

    fun getRole(token: String): String {
        return getClaims(token).get("role", String::class.java)
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(Base64.getDecoder().decode(secret))
            .parseClaimsJws(token)
            .body
    }
}
