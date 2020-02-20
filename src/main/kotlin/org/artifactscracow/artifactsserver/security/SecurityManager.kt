package org.artifactscracow.artifactsserver.security

import org.artifactscracow.artifactsserver.entities.User

interface SecurityManager {
    fun isAuthenticated(token: String): Boolean
    fun getUserFromToken(token: String): User?
}