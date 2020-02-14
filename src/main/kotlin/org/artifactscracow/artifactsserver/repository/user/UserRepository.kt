package org.artifactscracow.artifactsserver.repository.user

import org.artifactscracow.artifactsserver.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

internal interface UserRepository : JpaRepository<User, UUID>, UserRepositoryCustom {
    fun findByEmail(email: String): User?
}