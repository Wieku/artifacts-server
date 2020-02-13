package org.artifactscracow.artifactsserver.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "User")
@Table(name = "users")
class User {

    @Id
    @GeneratedValue
    private val id: UUID? = null

    var email: String? = null

    var passwordHashed: String? = null


}