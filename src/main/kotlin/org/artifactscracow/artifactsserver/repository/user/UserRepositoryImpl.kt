package org.artifactscracow.artifactsserver.repository.user

import org.artifactscracow.artifactsserver.entities.Artifact
import org.artifactscracow.artifactsserver.entities.User
import org.artifactscracow.artifactsserver.security.JWTHelper
import org.artifactscracow.artifactsserver.security.PBKDF2Secret
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

open class UserRepositoryImpl : UserRepositoryCustom {

    @PersistenceContext
    private lateinit var manager: EntityManager

    private val encoder = Pbkdf2PasswordEncoder(PBKDF2Secret)

    @Transactional
    override fun registerUser(email: String, password: String, name: String): User? {
        val user = User()

        user.email = email
        user.passwordHashed = encoder.encode(password)
        user.name = name

        manager.persist(user)
        manager.flush()

        return user
    }

    override fun authenticateUser(email: String, password: String): User? {
        val users = manager.createQuery("SELECT a FROM ${User::class.java.name} a WHERE a.email IS '$email'").resultList

        if (users.size == 0) return null

        val user = users[0] as User
        return if (!encoder.matches(password, user.passwordHashed)) null else user
    }

}