package org.artifactscracow.artifactsserver.repository.user

import org.artifactscracow.artifactsserver.entities.User

interface UserRepositoryCustom {
    fun registerUser(email: String, password: String, name: String): User?
    fun authenticateUser(email: String, password: String): User?
}
