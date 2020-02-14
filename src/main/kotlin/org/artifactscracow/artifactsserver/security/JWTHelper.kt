package org.artifactscracow.artifactsserver.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.artifactscracow.artifactsserver.entities.User
import java.util.*

object JWTHelper {

    fun extractToken(tokenEncoded: String): User? {
        return try {
            val claims = Jwts.parser().setSigningKey(JWTSecret).parseClaimsJws(tokenEncoded)

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

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, JWTSecret).compact()
    }

}