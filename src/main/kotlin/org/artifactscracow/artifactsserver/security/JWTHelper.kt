package org.artifactscracow.artifactsserver.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.artifactscracow.artifactsserver.entities.User
import java.util.*

object JWTHelper {

    fun extractToken(token: String): User? {
        if (!token.startsWith("Bearer ")) return null
        val token1 = token.substringAfter("Bearer ")

        return try {
            val claims = Jwts.parser().setSigningKey(JWTSecret).parseClaimsJws(token1)

            val user = User()
            user.email = claims.body.subject
            user.id = UUID.fromString(claims.body["userId"] as String)
            user.loginToken = UUID.fromString(claims.body["token"] as String)

            user
        } catch (e: Throwable) {
            null
        }
    }

    fun generateToken(user: User): String {
        val claims = Jwts.claims().setSubject(user.email)
        claims["userId"] = user.id.toString()
        claims["token"] = user.loginToken

        return "Bearer " + Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, JWTSecret).compact()
    }

}