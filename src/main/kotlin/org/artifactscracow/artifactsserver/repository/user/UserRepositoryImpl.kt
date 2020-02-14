package org.artifactscracow.artifactsserver.repository.user

import org.artifactscracow.artifactsserver.entities.User
import org.artifactscracow.artifactsserver.security.JWTHelper
import org.artifactscracow.artifactsserver.security.PBKDF2Secret
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

open class UserRepositoryImpl: UserRepositoryCustom {

    @PersistenceContext
    private lateinit var manager: EntityManager
    private val encoder = Pbkdf2PasswordEncoder(PBKDF2Secret)

    @Transactional
    override fun registerUser(email: String, password: String, name: String): String? {
        val user = User()

        user.email = email
        user.passwordHashed = encoder.encode(password)
        user.name = name

        manager.persist(user)
        manager.flush()

        return "Bearer " + JWTHelper.generateToken(user)
    }

    override fun loginUser(email: String, password: String): String? {
        val user = manager.find(User::class.java, null, mapOf(Pair("email", email))) ?: return null

        return "Bearer " + JWTHelper.generateToken(user)
    }

}