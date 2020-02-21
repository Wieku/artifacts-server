package org.artifactscracow.artifactsserver.security

import org.artifactscracow.artifactsserver.entities.User
import org.artifactscracow.artifactsserver.repository.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SecurityManagerImpl: SecurityManager {

    @Autowired
    private lateinit var repository: UserRepository

    override fun isAuthenticated(token: String): Boolean {
        val user = getUserFromToken(token) ?: return false
        val user1 = repository.findByEmail(user.email) ?: return false
        return user.id == user1.id && user.loginToken == user1.loginToken
    }

    override fun getUserFromToken(token: String): User? {
        val potentialUser = JWTHelper.extractToken(token) ?: return null
        val user1 = repository.findByEmail(potentialUser.email) ?: return null
        potentialUser.name = user1.name
        return potentialUser
    }

}