package org.artifactscracow.artifactsserver.security

interface SecurityManager {
    fun isAuthenticated(token: String): Boolean
}