package org.artifactscracow.artifactsserver.entities

import java.util.*
import javax.persistence.*

@Entity(name = "User")
@Table(name = "users")
class User {

    @Id
    @GeneratedValue
    lateinit var id: UUID

    var email: String = ""

    var name: String = ""

    var passwordHashed: String = ""

    var loginToken: UUID = UUID.randomUUID()

    @ManyToOne
    lateinit var role: UserRole
}