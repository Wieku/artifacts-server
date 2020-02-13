package org.artifactscracow.artifactsserver.entities

import org.artifactscracow.artifactsserver.permissions.Permission
import javax.persistence.*

@Entity(name = "UserRole")
@Table(name = "user_roles")
class UserRole() {

    constructor(roleName: String): this() {
        this.roleName = roleName
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int = 0
        private set

    var roleName: String = ""

    var isAdmin: Boolean = false

    @ElementCollection
    @CollectionTable(name = "permissions", joinColumns = [JoinColumn(name = "role_id")])
    @Column(name = "permission")
    val permissions: MutableList<Permission> = ArrayList()
}