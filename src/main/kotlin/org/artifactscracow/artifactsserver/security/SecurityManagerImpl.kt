package org.artifactscracow.artifactsserver.security

import org.artifactscracow.artifactsserver.repository.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SecurityManagerImpl: SecurityManager {

    @Autowired
    private lateinit var repository: UserRepository

    override fun isAuthenticated(token: String): Boolean {
        val user = JWTHelper.extractToken(token) ?: return false
        val user1 = repository.findByEmail(user.email) ?: return false
        return user.id == user1.id && user.loginToken == user1.loginToken
    }

}