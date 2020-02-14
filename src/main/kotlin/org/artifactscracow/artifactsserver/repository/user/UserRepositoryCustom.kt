package org.artifactscracow.artifactsserver.repository.user

interface UserRepositoryCustom {
    fun registerUser(email: String, password: String, name: String): String?
    fun loginUser(email: String, password: String): String?
}
